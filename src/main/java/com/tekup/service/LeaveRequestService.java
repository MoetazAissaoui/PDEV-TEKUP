package com.tekup.service;

import com.tekup.entity.LeaveRequest;
import com.tekup.entity.LeaveStatus;
import com.tekup.entity.User;
import com.tekup.events.LeaveRequestEvent;
import com.tekup.repository.LeaveRequestRepository;
import com.tekup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
    private UserRepository userRepository;
    
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        leaveRequest.setStatus(LeaveStatus.PENDING);
        return leaveRequestRepository.save(leaveRequest);
    }
    
    public LeaveRequest updateLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }
    
    public LeaveRequest approveLeaveRequest(Long requestId, String hrComment) {
        LeaveRequest request = leaveRequestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Leave request not found"));

        User user = request.getUser();
        int leaveDays = calculateLeaveDays(request.getStartDate(), request.getEndDate());

        if (user.getLeaveBalance() < leaveDays) {
            throw new RuntimeException("Insufficient leave balance");
        }

        user.setLeaveBalance(user.getLeaveBalance() - leaveDays);
        userRepository.save(user);

        request.setStatus(LeaveStatus.APPROVED);
        request.setHrComment(hrComment);
        LeaveRequest updatedRequest = leaveRequestRepository.save(request);
        
        // Publish event
        eventPublisher.publishEvent(new LeaveRequestEvent(this, updatedRequest));
        
        return updatedRequest;
    }
    
    public LeaveRequest rejectLeaveRequest(Long requestId, String hrComment) {
        LeaveRequest request = leaveRequestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Leave request not found"));
        request.setStatus(LeaveStatus.REJECTED);
        request.setHrComment(hrComment);
        return leaveRequestRepository.save(request);
    }
    
    public List<LeaveRequest> getPendingRequests() {
        return leaveRequestRepository.findByStatus(LeaveStatus.PENDING);
    }
    
    public List<LeaveRequest> getUserLeaveRequests(Long userId) {
        return leaveRequestRepository.findByUserId(userId);
    }

    public List<LeaveRequest> getLeaveHistory(Long userId) {
        return leaveRequestRepository.findByUserId(userId);
    }

    private int calculateLeaveDays(Date startDate, Date endDate) {
        // Implement logic to calculate the number of leave days
        long diff = endDate.getTime() - startDate.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
    }
} 
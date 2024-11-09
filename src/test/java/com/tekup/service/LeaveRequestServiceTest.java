package com.tekup.service;

import com.tekup.entity.LeaveRequest;
import com.tekup.entity.LeaveStatus;
import com.tekup.repository.LeaveRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LeaveRequestServiceTest {

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @InjectMocks
    private LeaveRequestService leaveRequestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLeaveRequest() {
        LeaveRequest leaveRequest = new LeaveRequest();
        when(leaveRequestRepository.save(any(LeaveRequest.class))).thenReturn(leaveRequest);

        LeaveRequest createdRequest = leaveRequestService.createLeaveRequest(leaveRequest);

        assertNotNull(createdRequest);
        assertEquals(LeaveStatus.PENDING, createdRequest.getStatus());
        verify(leaveRequestRepository, times(1)).save(leaveRequest);
    }

    @Test
    void testApproveLeaveRequest() {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        leaveRequest.setStatus(LeaveStatus.PENDING);

        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(leaveRequest));
        when(leaveRequestRepository.save(any(LeaveRequest.class))).thenReturn(leaveRequest);

        LeaveRequest approvedRequest = leaveRequestService.approveLeaveRequest(1L, "Approved by HR");

        assertEquals(LeaveStatus.APPROVED, approvedRequest.getStatus());
        verify(leaveRequestRepository, times(1)).save(leaveRequest);
    }

    @Test
    void testRejectLeaveRequest() {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(1L);
        leaveRequest.setStatus(LeaveStatus.PENDING);

        when(leaveRequestRepository.findById(1L)).thenReturn(Optional.of(leaveRequest));
        when(leaveRequestRepository.save(any(LeaveRequest.class))).thenReturn(leaveRequest);

        LeaveRequest rejectedRequest = leaveRequestService.rejectLeaveRequest(1L, "Rejected by HR");

        assertEquals(LeaveStatus.REJECTED, rejectedRequest.getStatus());
        verify(leaveRequestRepository, times(1)).save(leaveRequest);
    }

    @Test
    void testGetPendingRequests() {
        when(leaveRequestRepository.findByStatus(LeaveStatus.PENDING)).thenReturn(List.of(new LeaveRequest()));

        List<LeaveRequest> pendingRequests = leaveRequestService.getPendingRequests();

        assertFalse(pendingRequests.isEmpty());
        verify(leaveRequestRepository, times(1)).findByStatus(LeaveStatus.PENDING);
    }

    @Test
    void testGetUserLeaveRequests() {
        when(leaveRequestRepository.findByUserId(1L)).thenReturn(List.of(new LeaveRequest()));

        List<LeaveRequest> userRequests = leaveRequestService.getUserLeaveRequests(1L);

        assertFalse(userRequests.isEmpty());
        verify(leaveRequestRepository, times(1)).findByUserId(1L);
    }
} 
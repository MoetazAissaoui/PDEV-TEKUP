package com.tekup.controller;

import com.tekup.entity.LeaveRequest;
import com.tekup.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;
    
    @PostMapping
    public ResponseEntity<?> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        return ResponseEntity.ok(leaveRequestService.createLeaveRequest(leaveRequest));
    }
    
    @PreAuthorize("hasRole('HR')")
    @GetMapping("/pending")
    public ResponseEntity<List<LeaveRequest>> getPendingRequests() {
        return ResponseEntity.ok(leaveRequestService.getPendingRequests());
    }
    
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserLeaveRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(leaveRequestService.getUserLeaveRequests(userId));
    }
    
    @PreAuthorize("hasRole('HR')")
    @PutMapping("/{requestId}/approve")
    public ResponseEntity<LeaveRequest> approveRequest(
            @PathVariable Long requestId,
            @RequestParam(required = false) String hrComment) {
        return ResponseEntity.ok(leaveRequestService.approveLeaveRequest(requestId, hrComment));
    }
    
    @PreAuthorize("hasRole('HR')")
    @PutMapping("/{requestId}/reject")
    public ResponseEntity<LeaveRequest> rejectRequest(
            @PathVariable Long requestId,
            @RequestParam(required = false) String hrComment) {
        return ResponseEntity.ok(leaveRequestService.rejectLeaveRequest(requestId, hrComment));
    }
    
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/user/{userId}/history")
    public ResponseEntity<List<LeaveRequest>> getLeaveHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(leaveRequestService.getLeaveHistory(userId));
    }
} 
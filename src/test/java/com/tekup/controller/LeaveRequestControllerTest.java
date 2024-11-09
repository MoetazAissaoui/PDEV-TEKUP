package com.tekup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekup.entity.LeaveRequest;
import com.tekup.service.LeaveRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LeaveRequestController.class)
class LeaveRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeaveRequestService leaveRequestService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateLeaveRequest() throws Exception {
        LeaveRequest leaveRequest = new LeaveRequest();
        when(leaveRequestService.createLeaveRequest(any(LeaveRequest.class))).thenReturn(leaveRequest);

        mockMvc.perform(post("/api/leave-requests")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(leaveRequest)))
                .andExpect(status().isOk());

        verify(leaveRequestService, times(1)).createLeaveRequest(any(LeaveRequest.class));
    }

    @Test
    void testGetPendingRequests() throws Exception {
        when(leaveRequestService.getPendingRequests()).thenReturn(List.of(new LeaveRequest()));

        mockMvc.perform(get("/api/leave-requests/pending"))
                .andExpect(status().isOk());

        verify(leaveRequestService, times(1)).getPendingRequests();
    }

    @Test
    void testApproveRequest() throws Exception {
        LeaveRequest leaveRequest = new LeaveRequest();
        when(leaveRequestService.approveLeaveRequest(anyLong(), anyString())).thenReturn(leaveRequest);

        mockMvc.perform(put("/api/leave-requests/1/approve")
                .param("hrComment", "Approved"))
                .andExpect(status().isOk());

        verify(leaveRequestService, times(1)).approveLeaveRequest(1L, "Approved");
    }
} 
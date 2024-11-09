package com.tekup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekup.entity.Timesheet;
import com.tekup.service.TimesheetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TimesheetController.class)
class TimesheetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimesheetService timesheetService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTimesheet() throws Exception {
        Timesheet timesheet = new Timesheet();
        when(timesheetService.createTimesheet(any(Timesheet.class))).thenReturn(timesheet);

        mockMvc.perform(post("/api/timesheets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(timesheet)))
                .andExpect(status().isOk());

        verify(timesheetService, times(1)).createTimesheet(any(Timesheet.class));
    }

    @Test
    void testGetUserTimesheets() throws Exception {
        when(timesheetService.getUserTimesheets(1L)).thenReturn(List.of(new Timesheet()));

        mockMvc.perform(get("/api/timesheets/user/1"))
                .andExpect(status().isOk());

        verify(timesheetService, times(1)).getUserTimesheets(1L);
    }

    @Test
    void testApproveTimesheet() throws Exception {
        Timesheet timesheet = new Timesheet();
        when(timesheetService.approveTimesheet(anyLong(), anyString())).thenReturn(timesheet);

        mockMvc.perform(put("/api/timesheets/1/approve")
                .param("comments", "Approved"))
                .andExpect(status().isOk());

        verify(timesheetService, times(1)).approveTimesheet(1L, "Approved");
    }
} 
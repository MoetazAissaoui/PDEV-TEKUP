package com.tekup.service;

import com.tekup.entity.Timesheet;
import com.tekup.entity.TimesheetStatus;
import com.tekup.repository.TimesheetRepository;
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

class TimesheetServiceTest {

    @Mock
    private TimesheetRepository timesheetRepository;

    @InjectMocks
    private TimesheetService timesheetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTimesheet() {
        Timesheet timesheet = new Timesheet();
        when(timesheetRepository.save(any(Timesheet.class))).thenReturn(timesheet);

        Timesheet createdTimesheet = timesheetService.createTimesheet(timesheet);

        assertNotNull(createdTimesheet);
        assertEquals(TimesheetStatus.PENDING, createdTimesheet.getStatus());
        verify(timesheetRepository, times(1)).save(timesheet);
    }

    @Test
    void testApproveTimesheet() {
        Timesheet timesheet = new Timesheet();
        timesheet.setId(1L);
        timesheet.setStatus(TimesheetStatus.PENDING);

        when(timesheetRepository.findById(1L)).thenReturn(Optional.of(timesheet));
        when(timesheetRepository.save(any(Timesheet.class))).thenReturn(timesheet);

        Timesheet approvedTimesheet = timesheetService.approveTimesheet(1L, "Approved by HR");

        assertEquals(TimesheetStatus.APPROVED, approvedTimesheet.getStatus());
        verify(timesheetRepository, times(1)).save(timesheet);
    }

    @Test
    void testRejectTimesheet() {
        Timesheet timesheet = new Timesheet();
        timesheet.setId(1L);
        timesheet.setStatus(TimesheetStatus.PENDING);

        when(timesheetRepository.findById(1L)).thenReturn(Optional.of(timesheet));
        when(timesheetRepository.save(any(Timesheet.class))).thenReturn(timesheet);

        Timesheet rejectedTimesheet = timesheetService.rejectTimesheet(1L, "Rejected by HR");

        assertEquals(TimesheetStatus.REJECTED, rejectedTimesheet.getStatus());
        verify(timesheetRepository, times(1)).save(timesheet);
    }

    @Test
    void testGetPendingTimesheets() {
        when(timesheetRepository.findByStatus(TimesheetStatus.PENDING)).thenReturn(List.of(new Timesheet()));

        List<Timesheet> pendingTimesheets = timesheetService.getPendingTimesheets();

        assertFalse(pendingTimesheets.isEmpty());
        verify(timesheetRepository, times(1)).findByStatus(TimesheetStatus.PENDING);
    }

    @Test
    void testGetUserTimesheets() {
        when(timesheetRepository.findByUserId(1L)).thenReturn(List.of(new Timesheet()));

        List<Timesheet> userTimesheets = timesheetService.getUserTimesheets(1L);

        assertFalse(userTimesheets.isEmpty());
        verify(timesheetRepository, times(1)).findByUserId(1L);
    }
} 
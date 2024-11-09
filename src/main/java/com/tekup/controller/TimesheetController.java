package com.tekup.controller;

import com.tekup.entity.Timesheet;
import com.tekup.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {

    @Autowired
    private TimesheetService timesheetService;

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Timesheet>> getUserTimesheets(@PathVariable Long userId) {
        return ResponseEntity.ok(timesheetService.getUserTimesheets(userId));
    }

    @PreAuthorize("hasRole('HR') or hasRole('MANAGER')")
    @PutMapping("/{timesheetId}/approve")
    public ResponseEntity<Timesheet> approveTimesheet(
            @PathVariable Long timesheetId,
            @RequestParam(required = false) String comments) {
        return ResponseEntity.ok(timesheetService.approveTimesheet(timesheetId, comments));
    }

    @PreAuthorize("hasRole('HR') or hasRole('MANAGER')")
    @PutMapping("/{timesheetId}/reject")
    public ResponseEntity<Timesheet> rejectTimesheet(
            @PathVariable Long timesheetId,
            @RequestParam(required = false) String comments) {
        return ResponseEntity.ok(timesheetService.rejectTimesheet(timesheetId, comments));
    }
} 
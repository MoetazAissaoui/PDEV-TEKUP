package com.tekup.service;

import com.tekup.entity.Timesheet;
import com.tekup.entity.TimesheetStatus;
import com.tekup.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    public Timesheet createTimesheet(Timesheet timesheet) {
        timesheet.setStatus(TimesheetStatus.PENDING);
        return timesheetRepository.save(timesheet);
    }

    public Timesheet updateTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public void deleteTimesheet(Long timesheetId) {
        timesheetRepository.deleteById(timesheetId);
    }

    public List<Timesheet> getUserTimesheets(Long userId) {
        return timesheetRepository.findByUserId(userId);
    }

    public Timesheet getTimesheet(Long timesheetId) {
        return timesheetRepository.findById(timesheetId)
            .orElseThrow(() -> new RuntimeException("Timesheet not found"));
    }

    public Timesheet approveTimesheet(Long timesheetId, String comments) {
        Timesheet timesheet = timesheetRepository.findById(timesheetId)
            .orElseThrow(() -> new RuntimeException("Timesheet not found"));
        timesheet.setStatus(TimesheetStatus.APPROVED);
        timesheet.setComments(comments);
        return timesheetRepository.save(timesheet);
    }

    public Timesheet rejectTimesheet(Long timesheetId, String comments) {
        Timesheet timesheet = timesheetRepository.findById(timesheetId)
            .orElseThrow(() -> new RuntimeException("Timesheet not found"));
        timesheet.setStatus(TimesheetStatus.REJECTED);
        timesheet.setComments(comments);
        return timesheetRepository.save(timesheet);
    }

    public List<Timesheet> getPendingTimesheets() {
        return timesheetRepository.findByStatus(TimesheetStatus.PENDING);
    }
} 
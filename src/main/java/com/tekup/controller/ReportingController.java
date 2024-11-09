package com.tekup.controller;

import com.tekup.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/leave-count")
    public ResponseEntity<Map<Long, Long>> getLeaveCountByUser() {
        return ResponseEntity.ok(reportingService.getLeaveCountByUser());
    }

    @GetMapping("/average-performance")
    public ResponseEntity<Map<Long, Double>> getAveragePerformanceScoreByUser() {
        return ResponseEntity.ok(reportingService.getAveragePerformanceScoreByUser());
    }

    @GetMapping("/total-hours")
    public ResponseEntity<Map<Long, Integer>> getTotalHoursWorkedByUser() {
        return ResponseEntity.ok(reportingService.getTotalHoursWorkedByUser());
    }
} 
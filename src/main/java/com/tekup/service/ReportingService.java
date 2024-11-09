package com.tekup.service;

import com.tekup.entity.LeaveRequest;
import com.tekup.entity.PerformanceEvaluation;
import com.tekup.entity.Timesheet;
import com.tekup.repository.LeaveRequestRepository;
import com.tekup.repository.PerformanceEvaluationRepository;
import com.tekup.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private PerformanceEvaluationRepository performanceEvaluationRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    public Map<Long, Long> getLeaveCountByUser() {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        return leaveRequests.stream()
                .collect(Collectors.groupingBy(lr -> lr.getUser().getId(), Collectors.counting()));
    }

    public Map<Long, Double> getAveragePerformanceScoreByUser() {
        List<PerformanceEvaluation> evaluations = performanceEvaluationRepository.findAll();
        return evaluations.stream()
                .collect(Collectors.groupingBy(pe -> pe.getUser().getId(),
                        Collectors.averagingInt(PerformanceEvaluation::getScore)));
    }

    public Map<Long, Integer> getTotalHoursWorkedByUser() {
        List<Timesheet> timesheets = timesheetRepository.findAll();
        return timesheets.stream()
                .collect(Collectors.groupingBy(ts -> ts.getUser().getId(),
                        Collectors.summingInt(Timesheet::getHoursWorked)));
    }
} 
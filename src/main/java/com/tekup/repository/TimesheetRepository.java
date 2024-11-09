package com.tekup.repository;

import com.tekup.entity.Timesheet;
import com.tekup.entity.TimesheetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByUserId(Long userId);
    List<Timesheet> findByStatus(TimesheetStatus status);
} 
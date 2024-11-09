package com.tekup.repository;

import com.tekup.entity.PerformanceEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceEvaluationRepository extends JpaRepository<PerformanceEvaluation, Long> {
    List<PerformanceEvaluation> findByUserId(Long userId);
} 
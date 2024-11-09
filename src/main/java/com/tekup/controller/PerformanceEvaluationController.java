package com.tekup.controller;

import com.tekup.entity.EvaluationCriteria;
import com.tekup.entity.PerformanceEvaluation;
import com.tekup.service.PerformanceEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/performance-evaluations")
public class PerformanceEvaluationController {

    @Autowired
    private PerformanceEvaluationService performanceEvaluationService;

    @PreAuthorize("hasRole('HR') or hasRole('MANAGER')")
    @PostMapping("/user/{userId}")
    public ResponseEntity<PerformanceEvaluation> createEvaluation(
            @PathVariable Long userId,
            @RequestParam String evaluator,
            @RequestBody Map<EvaluationCriteria, Integer> criteriaScores,
            @RequestParam String comments) {
        PerformanceEvaluation evaluation = performanceEvaluationService.createEvaluation(userId, evaluator, criteriaScores, comments);
        return ResponseEntity.ok(evaluation);
    }

    @PutMapping("/{evaluationId}")
    public ResponseEntity<?> updateEvaluation(@PathVariable Long evaluationId, @RequestBody PerformanceEvaluation evaluation) {
        evaluation.setId(evaluationId);
        return ResponseEntity.ok(performanceEvaluationService.updateEvaluation(evaluation));
    }

    @DeleteMapping("/{evaluationId}")
    public ResponseEntity<?> deleteEvaluation(@PathVariable Long evaluationId) {
        performanceEvaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PerformanceEvaluation>> getUserEvaluations(@PathVariable Long userId) {
        return ResponseEntity.ok(performanceEvaluationService.getUserEvaluations(userId));
    }

    @GetMapping("/{evaluationId}")
    public ResponseEntity<PerformanceEvaluation> getEvaluation(@PathVariable Long evaluationId) {
        return ResponseEntity.ok(performanceEvaluationService.getEvaluation(evaluationId));
    }
} 
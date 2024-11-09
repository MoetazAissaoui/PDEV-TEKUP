package com.tekup.controller;

import com.tekup.entity.EvaluationCriteria;
import com.tekup.service.EvaluationCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation-criteria")
public class EvaluationCriteriaController {

    @Autowired
    private EvaluationCriteriaService evaluationCriteriaService;

    @PostMapping
    public ResponseEntity<EvaluationCriteria> createCriteria(@RequestBody EvaluationCriteria criteria) {
        return ResponseEntity.ok(evaluationCriteriaService.createCriteria(criteria));
    }

    @GetMapping
    public ResponseEntity<List<EvaluationCriteria>> getAllCriteria() {
        return ResponseEntity.ok(evaluationCriteriaService.getAllCriteria());
    }

    // Additional endpoints for updating and deleting criteria
} 
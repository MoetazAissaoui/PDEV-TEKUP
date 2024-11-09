package com.tekup.service;

import com.tekup.entity.EvaluationCriteria;
import com.tekup.repository.EvaluationCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationCriteriaService {

    @Autowired
    private EvaluationCriteriaRepository evaluationCriteriaRepository;

    public EvaluationCriteria createCriteria(EvaluationCriteria criteria) {
        return evaluationCriteriaRepository.save(criteria);
    }

    public List<EvaluationCriteria> getAllCriteria() {
        return evaluationCriteriaRepository.findAll();
    }

    // Additional methods for updating and deleting criteria
} 
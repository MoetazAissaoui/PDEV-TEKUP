package com.tekup.service;

import com.tekup.entity.EvaluationCriteria;
import com.tekup.entity.PerformanceEvaluation;
import com.tekup.entity.User;
import com.tekup.repository.PerformanceEvaluationRepository;
import com.tekup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class PerformanceEvaluationService {

    @Autowired
    private PerformanceEvaluationRepository performanceEvaluationRepository;

    @Autowired
    private UserRepository userRepository;

    public PerformanceEvaluation createEvaluation(Long userId, String evaluator, Map<EvaluationCriteria, Integer> criteriaScores, String comments) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PerformanceEvaluation evaluation = new PerformanceEvaluation();
        evaluation.setUser(user);
        evaluation.setEvaluationDate(new Date());
        evaluation.setEvaluator(evaluator);
        evaluation.setCriteriaScores(criteriaScores);
        evaluation.setComments(comments);

        return performanceEvaluationRepository.save(evaluation);
    }

    public PerformanceEvaluation updateEvaluation(PerformanceEvaluation evaluation) {
        return performanceEvaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(Long evaluationId) {
        performanceEvaluationRepository.deleteById(evaluationId);
    }

    public List<PerformanceEvaluation> getUserEvaluations(Long userId) {
        return performanceEvaluationRepository.findByUserId(userId);
    }

    public PerformanceEvaluation getEvaluation(Long evaluationId) {
        return performanceEvaluationRepository.findById(evaluationId)
            .orElseThrow(() -> new RuntimeException("Evaluation not found"));
    }
} 
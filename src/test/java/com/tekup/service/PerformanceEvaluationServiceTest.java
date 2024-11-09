package com.tekup.service;

import com.tekup.entity.PerformanceEvaluation;
import com.tekup.repository.PerformanceEvaluationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PerformanceEvaluationServiceTest {

    @Mock
    private PerformanceEvaluationRepository performanceEvaluationRepository;

    @InjectMocks
    private PerformanceEvaluationService performanceEvaluationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvaluation() {
        PerformanceEvaluation evaluation = new PerformanceEvaluation();
        when(performanceEvaluationRepository.save(any(PerformanceEvaluation.class))).thenReturn(evaluation);

        PerformanceEvaluation createdEvaluation = performanceEvaluationService.createEvaluation(evaluation);

        assertNotNull(createdEvaluation);
        verify(performanceEvaluationRepository, times(1)).save(evaluation);
    }

    @Test
    void testUpdateEvaluation() {
        PerformanceEvaluation evaluation = new PerformanceEvaluation();
        when(performanceEvaluationRepository.save(any(PerformanceEvaluation.class))).thenReturn(evaluation);

        PerformanceEvaluation updatedEvaluation = performanceEvaluationService.updateEvaluation(evaluation);

        assertNotNull(updatedEvaluation);
        verify(performanceEvaluationRepository, times(1)).save(evaluation);
    }

    @Test
    void testDeleteEvaluation() {
        doNothing().when(performanceEvaluationRepository).deleteById(1L);

        performanceEvaluationService.deleteEvaluation(1L);

        verify(performanceEvaluationRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetUserEvaluations() {
        when(performanceEvaluationRepository.findByUserId(1L)).thenReturn(List.of(new PerformanceEvaluation()));

        List<PerformanceEvaluation> evaluations = performanceEvaluationService.getUserEvaluations(1L);

        assertFalse(evaluations.isEmpty());
        verify(performanceEvaluationRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetEvaluation() {
        PerformanceEvaluation evaluation = new PerformanceEvaluation();
        when(performanceEvaluationRepository.findById(1L)).thenReturn(Optional.of(evaluation));

        PerformanceEvaluation foundEvaluation = performanceEvaluationService.getEvaluation(1L);

        assertNotNull(foundEvaluation);
        verify(performanceEvaluationRepository, times(1)).findById(1L);
    }
} 
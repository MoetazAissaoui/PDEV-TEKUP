package com.tekup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekup.entity.PerformanceEvaluation;
import com.tekup.service.PerformanceEvaluationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PerformanceEvaluationController.class)
class PerformanceEvaluationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerformanceEvaluationService performanceEvaluationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEvaluation() throws Exception {
        PerformanceEvaluation evaluation = new PerformanceEvaluation();
        when(performanceEvaluationService.createEvaluation(any(PerformanceEvaluation.class))).thenReturn(evaluation);

        mockMvc.perform(post("/api/performance-evaluations")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(evaluation)))
                .andExpect(status().isOk());

        verify(performanceEvaluationService, times(1)).createEvaluation(any(PerformanceEvaluation.class));
    }

    @Test
    void testGetUserEvaluations() throws Exception {
        when(performanceEvaluationService.getUserEvaluations(1L)).thenReturn(List.of(new PerformanceEvaluation()));

        mockMvc.perform(get("/api/performance-evaluations/user/1"))
                .andExpect(status().isOk());

        verify(performanceEvaluationService, times(1)).getUserEvaluations(1L);
    }
} 
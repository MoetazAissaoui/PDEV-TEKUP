package com.tekup.events;

import com.tekup.entity.PerformanceEvaluation;
import org.springframework.context.ApplicationEvent;

public class PerformanceEvaluationEvent extends ApplicationEvent {
    private final PerformanceEvaluation evaluation;

    public PerformanceEvaluationEvent(Object source, PerformanceEvaluation evaluation) {
        super(source);
        this.evaluation = evaluation;
    }

    public PerformanceEvaluation getEvaluation() {
        return evaluation;
    }
} 
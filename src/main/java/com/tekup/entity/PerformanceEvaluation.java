package com.tekup.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class PerformanceEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date evaluationDate;

    @Column(nullable = false)
    private String evaluator; // Name or ID of the evaluator

    @ElementCollection
    @CollectionTable(name = "evaluation_scores", joinColumns = @JoinColumn(name = "evaluation_id"))
    @MapKeyJoinColumn(name = "criteria_id")
    @Column(name = "score")
    private Map<EvaluationCriteria, Integer> criteriaScores = new HashMap<>();

    @Column(length = 1000)
    private String comments;

    // Getters and Setters
    // ... add all getters and setters
} 
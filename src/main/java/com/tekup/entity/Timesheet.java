package com.tekup.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private int hoursWorked;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TimesheetStatus status = TimesheetStatus.PENDING;

    @Column(length = 1000)
    private String comments;

    // Getters and Setters
    // ... add all getters and setters
} 
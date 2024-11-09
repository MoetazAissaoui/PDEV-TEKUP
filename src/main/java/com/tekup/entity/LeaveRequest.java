package com.tekup.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LeaveRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.PENDING;
    
    @Column(length = 1000)
    private String reason;
    
    @Column
    private String hrComment;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;
    
    // Getters and Setters
    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    // ... add all getters and setters
} 
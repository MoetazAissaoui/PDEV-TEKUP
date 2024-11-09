package com.tekup.events;

import com.tekup.entity.LeaveRequest;
import org.springframework.context.ApplicationEvent;

public class LeaveRequestEvent extends ApplicationEvent {
    private final LeaveRequest leaveRequest;

    public LeaveRequestEvent(Object source, LeaveRequest leaveRequest) {
        super(source);
        this.leaveRequest = leaveRequest;
    }

    public LeaveRequest getLeaveRequest() {
        return leaveRequest;
    }
} 
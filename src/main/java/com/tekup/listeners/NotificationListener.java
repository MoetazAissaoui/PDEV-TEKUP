package com.tekup.listeners;

import com.tekup.events.LeaveRequestEvent;
import com.tekup.events.PerformanceEvaluationEvent;
import com.tekup.events.ReminderEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @EventListener
    public void handleLeaveRequestEvent(LeaveRequestEvent event) {
        // Logic to send notification for leave request
        System.out.println("Notification: Leave request status changed for user " + event.getLeaveRequest().getUser().getId());
    }

    @EventListener
    public void handlePerformanceEvaluationEvent(PerformanceEvaluationEvent event) {
        // Logic to send notification for performance evaluation
        System.out.println("Notification: New performance evaluation for user " + event.getEvaluation().getUser().getId());
    }

    @EventListener
    public void handleReminderEvent(ReminderEvent event) {
        // Logic to send reminder notification
        System.out.println("Reminder: " + event.getMessage());
    }
} 
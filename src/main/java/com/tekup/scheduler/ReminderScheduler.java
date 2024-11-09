package com.tekup.scheduler;

import com.tekup.events.ReminderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReminderScheduler {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0 0 9 * * ?") // Every day at 9 AM
    public void sendDailyReminders() {
        eventPublisher.publishEvent(new ReminderEvent(this, "Daily reminder: Please submit your timesheets."));
    }
} 
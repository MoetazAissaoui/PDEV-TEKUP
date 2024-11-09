package com.tekup.events;

import org.springframework.context.ApplicationEvent;

public class ReminderEvent extends ApplicationEvent {
    private final String message;

    public ReminderEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
} 
package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 24.05.17.
 */
public class ScheduleNotFoundException extends ScheduleException {
    public ScheduleNotFoundException(String message) {
        super(message);
    }

    public ScheduleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

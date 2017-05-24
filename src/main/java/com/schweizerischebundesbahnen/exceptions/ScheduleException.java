package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 24.05.17.
 */
public class ScheduleException extends RuntimeException {
    public ScheduleException(String message) {
        super(message);
    }

    public ScheduleException(String message, Throwable cause) {
        super(message, cause);
    }
}

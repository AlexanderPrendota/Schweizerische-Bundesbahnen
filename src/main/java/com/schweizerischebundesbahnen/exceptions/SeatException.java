package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 12.04.17.
 */
public class SeatException extends RuntimeException {
    public SeatException(String message) {
        super(message);
    }

    public SeatException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 07.04.17.
 */
public class StationException extends RuntimeException {
    public StationException(String message) {
        super(message);
    }

    public StationException(String message, Throwable cause) {
        super(message, cause);
    }

}

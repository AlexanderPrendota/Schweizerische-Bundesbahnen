package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 07.04.17.
 */
public class StationNotFoundException extends StationException {
    public StationNotFoundException(String message) {
        super(message);
    }

    public StationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

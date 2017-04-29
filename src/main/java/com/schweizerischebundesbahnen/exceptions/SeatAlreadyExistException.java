package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 12.04.17.
 */
public class SeatAlreadyExistException extends SeatException {

    public SeatAlreadyExistException(String message) {
        super(message);
    }

    public SeatAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

}

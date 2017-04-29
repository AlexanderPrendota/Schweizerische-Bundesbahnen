package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 07.04.17.
 */
public class TrainException extends RuntimeException{
    public TrainException(String message) {
        super(message);
    }

    public TrainException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 07.04.17.
 */
public class TrainNotFoundException extends TrainException {
    public TrainNotFoundException(String message) {
        super(message);
    }

    public TrainNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

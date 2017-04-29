package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 30.03.17.
 */
public class UserException extends RuntimeException  {

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

}

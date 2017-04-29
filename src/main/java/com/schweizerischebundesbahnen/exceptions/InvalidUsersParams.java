package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 30.03.17.
 */

public class InvalidUsersParams extends UserException {

    public InvalidUsersParams(String message) {
        super(message);
    }

    public InvalidUsersParams(String message, Throwable cause) {
        super(message, cause);
    }

}

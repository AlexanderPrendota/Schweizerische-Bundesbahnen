package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 27.03.17.
 */

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

}

package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 27.03.17.
 */

public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

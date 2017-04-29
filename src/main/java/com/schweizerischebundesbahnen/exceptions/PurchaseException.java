package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 01.04.17.
 */
public class PurchaseException extends RuntimeException {

    public PurchaseException(String message) {
        super(message);
    }

    public PurchaseException(String message, Throwable cause) {
        super(message, cause);
    }

}

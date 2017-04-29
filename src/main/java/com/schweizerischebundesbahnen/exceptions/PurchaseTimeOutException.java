package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 01.04.17.
 */
public class PurchaseTimeOutException extends PurchaseException {

    public PurchaseTimeOutException(String message) {
        super(message);
    }

    public PurchaseTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
}

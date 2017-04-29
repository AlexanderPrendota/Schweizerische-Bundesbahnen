package com.schweizerischebundesbahnen.exceptions;

/**
 * Created by aleksandrprendota on 01.04.17.
 */
public class PurchaseAlreadyExistExceprion extends PurchaseException{

    public PurchaseAlreadyExistExceprion(String message) {
        super(message);
    }

    public PurchaseAlreadyExistExceprion(String message, Throwable cause) {
        super(message, cause);
    }
}

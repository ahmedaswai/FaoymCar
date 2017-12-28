package com.qcar.model.mongo.service.exception;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class QCarSecurityException extends QCarException {

    public QCarSecurityException(String msg) {
        super(msg);
    }

    public QCarSecurityException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

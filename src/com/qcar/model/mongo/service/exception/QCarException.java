package com.qcar.model.mongo.service.exception;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class QCarException extends RuntimeException {

    public QCarException(String msg) {
        super(msg);
    }

    public QCarException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

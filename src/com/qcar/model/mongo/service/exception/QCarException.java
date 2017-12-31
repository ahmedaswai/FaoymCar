package com.qcar.model.mongo.service.exception;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class QCarException extends RuntimeException {

    private final ErrorCodes errorCode;
    public QCarException(ErrorCodes errorCode) {

        super(errorCode.getErrorMessage());
        this.errorCode=errorCode;
    }

    public QCarException(Throwable throwable,ErrorCodes errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode=errorCode;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }
}

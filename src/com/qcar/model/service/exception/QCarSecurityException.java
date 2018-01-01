package com.qcar.model.service.exception;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class QCarSecurityException extends QCarException {

    public QCarSecurityException(ErrorCodes errorCode) {
        super(errorCode);
    }

    public QCarSecurityException(Throwable throwable,ErrorCodes errorCode) {

        super(throwable,errorCode);
    }

}

package com.qcar.model.service;

import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.utils.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class ServiceError {

    @JsonProperty("sc")
    private Integer statusCode;

    @JsonProperty("v")
    private String version = Constants.VERSION;

    @JsonProperty("ec")
    private ErrorCodes errorCode;

    @JsonProperty("ms")
    private String errorMessage;

    @JsonProperty("lm")
    private String logMessage;

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getVersion() {
        return version;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static final Buffer response(Throwable e, ErrorCodes errorCode) {
        ServiceError returnSingle = new ServiceError();
        returnSingle.errorCode = errorCode;
        returnSingle.logMessage=e.getMessage();

        returnSingle.statusCode = 500;

        return Json.encodeToBuffer(returnSingle);
    }

    public String getLogMessage() {
        return logMessage;
    }

    public static final Buffer response(Throwable e) {
        ServiceError returnSingle = new ServiceError();

        returnSingle.errorCode=ErrorCodes.UN_DEFINED_EXCEPTION;
        returnSingle.statusCode = 500;
        returnSingle.logMessage=e.getMessage();
        returnSingle.errorMessage=ErrorCodes.UN_DEFINED_EXCEPTION.getErrorMessage();
        return Json.encodeToBuffer(returnSingle);
    }
}

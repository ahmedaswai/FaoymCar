package com.qcar.model.mongo.service;

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

    @JsonProperty("ms")
    private String errorMessage;

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getVersion() {
        return version;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static final Buffer response(String ms) {
        ServiceError returnSingle = new ServiceError();
        returnSingle.errorMessage = ms;
        returnSingle.statusCode = 500;
        return Json.encodeToBuffer(returnSingle);
    }
}

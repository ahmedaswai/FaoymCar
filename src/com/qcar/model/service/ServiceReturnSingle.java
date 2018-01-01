package com.qcar.model.service;

import com.qcar.utils.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class ServiceReturnSingle<T> {
    private ServiceReturnSingle() {

    }

    @JsonProperty("sc")
    private Integer statusCode;

    @JsonProperty("v")
    private String version = Constants.VERSION;

    @JsonProperty("rs")
    private T result;


    public Integer getStatusCode() {
        return statusCode;
    }

    public T getResult() {
        return result;
    }

    public String getVersion() {
        return version;
    }

    public static final Buffer response(Object t) {
        ServiceReturnSingle returnSingle = new ServiceReturnSingle();
        returnSingle.result = t;
        returnSingle.statusCode = HttpsURLConnection.HTTP_OK;
        return Json.encodeToBuffer(returnSingle);
    }
}

package com.qcar.model.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qcar.utils.Constants;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;

import javax.net.ssl.HttpsURLConnection;
import java.util.Map;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class ServiceReturnMap<K,V> {
    private ServiceReturnMap() {

    }

    @JsonProperty("sc")
    private Integer statusCode;

    @JsonProperty("v")
    private String version = Constants.VERSION;

    @JsonProperty("rs")
    private Map<K,V> result;


    public Integer getStatusCode() {
        return statusCode;
    }

    public Map<K, V> getResult() {
        return result;
    }

    public String getVersion() {
        return version;
    }

    public static final Buffer response(Map<?,?> t) {
        ServiceReturnMap returnSingle = new ServiceReturnMap();
        returnSingle.result = t;
        returnSingle.statusCode = HttpsURLConnection.HTTP_OK;
        return Json.encodeToBuffer(returnSingle);
    }
}

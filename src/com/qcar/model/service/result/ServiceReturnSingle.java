package com.qcar.model.service.result;

import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarException;
import com.qcar.utils.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;

import javax.net.ssl.HttpsURLConnection;
import java.util.Optional;

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
        if(t instanceof Optional){
            return response((Optional<Object>) t);
        }
        ServiceReturnSingle returnSingle = new ServiceReturnSingle();
        returnSingle.result = t;
        returnSingle.statusCode = HttpsURLConnection.HTTP_OK;
        return Json.encodeToBuffer(returnSingle);
    }
    private static final Buffer response(Optional<Object> t) {

        if(!t.isPresent()){
            throw new QCarException(ErrorCodes.ENTITY_NOT_FOUND);
        }
        ServiceReturnSingle returnSingle = new ServiceReturnSingle();
        returnSingle.result = t.get();
        returnSingle.statusCode = HttpsURLConnection.HTTP_OK;
        return Json.encodeToBuffer(returnSingle);
    }


}

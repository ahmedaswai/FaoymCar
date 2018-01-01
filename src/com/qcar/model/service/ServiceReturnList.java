package com.qcar.model.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qcar.utils.CollectionUtils;
import com.qcar.utils.Constants;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class ServiceReturnList<T> {
    private ServiceReturnList() {

    }


    @JsonProperty("c")
    private Integer count=0;

    @JsonProperty("sc")
    private Integer statusCode;

    @JsonProperty("v")
    private String version = Constants.VERSION;

    @JsonProperty("rs")
    private List<T> result;


    public Integer getStatusCode() {
        return statusCode;
    }

    public List<T> getResult() {
        return result;
    }

    public String getVersion() {
        return version;
    }

    public static final Buffer response(List<?> list) {
        ServiceReturnList returnSingle = new ServiceReturnList();
        returnSingle.result = list;
        returnSingle.statusCode = HttpsURLConnection.HTTP_OK;
        if(!CollectionUtils.isEmpty(list)){
            returnSingle.count=list.size();
        }
        return Json.encodeToBuffer(returnSingle);
    }
}

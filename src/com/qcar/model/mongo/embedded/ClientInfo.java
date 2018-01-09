package com.qcar.model.mongo.embedded;

import io.vertx.core.http.HttpServerRequest;

public final class ClientInfo {

    private String uri;
    private String agent;
    private String method;
    private String clientIp;
    private Integer clientPort;


    public ClientInfo(){

    }
    public static final ClientInfo instance(HttpServerRequest request){
        ClientInfo info=new ClientInfo();
        info.uri=request.absoluteURI();
        info.agent=request.getHeader("User-Agent");
        info.method=request.method().name();
        info.clientIp=request.remoteAddress().host();
        info.clientPort=request.remoteAddress().port();
        return info;
    }

    public String getUri() {
        return uri;
    }

    public String getAgent() {
        return agent;
    }

    public String getMethod() {
        return method;
    }

    public String getClientIp() {
        return clientIp;
    }

    public Integer getClientPort() {
        return clientPort;
    }
}

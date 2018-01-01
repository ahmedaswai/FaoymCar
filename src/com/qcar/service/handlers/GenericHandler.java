package com.qcar.service.handlers;

import com.qcar.model.mongo.GenericEntity;
import com.qcar.model.service.ClientInfo;
import io.vertx.ext.web.RoutingContext;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GenericHandler {
    GenericHandler(){}
    public void setClientInfo(GenericEntity entity,RoutingContext ctx){
        Map<String,Object>clientInfo=new HashMap<>();



       entity.setUpdatedOn(new Date());
       entity.setClientInfo(ClientInfo.instance(ctx.request()));

    }
}

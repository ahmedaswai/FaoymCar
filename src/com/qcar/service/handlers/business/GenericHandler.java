package com.qcar.service.handlers.business;

import com.qcar.model.mongo.GenericEntity;
import com.qcar.model.mongo.User;
import com.qcar.model.service.ClientInfo;
import io.vertx.ext.web.RoutingContext;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GenericHandler {
    GenericHandler(){}
    public void setClientInfo(GenericEntity entity,RoutingContext ctx){

        User user=(User) ctx.data().get("user");
       entity.updatedOn(new Date())
               .updatedBy(user.getId()).
                clientInfo(ClientInfo.instance(ctx.request()));

    }
}

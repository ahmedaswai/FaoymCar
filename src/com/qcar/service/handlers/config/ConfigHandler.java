package com.qcar.service.handlers.config;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class ConfigHandler  implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext ctx) {
        JsonObject object=new JsonObject();
        object.put("web-root-dir","/Volumes/Bmind-Data/Q-Car/web-root");
        ctx.data().put("config",object);
        ctx.next();

    }
}

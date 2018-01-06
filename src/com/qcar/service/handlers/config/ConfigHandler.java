package com.qcar.service.handlers.config;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarException;
import com.qcar.utils.Constants;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigHandler  implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext ctx) {


        JsonObject object=new JsonObject();
        object.put("web-root-dir","/Volumes/Bmind-Data/Q-Car/web-root");
        ctx.data().put("config",object);
        ctx.next();

    }

}

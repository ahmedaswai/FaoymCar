package com.car.fayoum.service;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

/**
 * Created by ahmedissawi on 12/10/17.
 */
public class VertxLauncher {

    public static void main (String[]args){
        Vertx vertx=Vertx.vertx();
        HttpServer server = vertx.createHttpServer();


        Router mainRouter = Router.router(vertx);

        mainRouter.route(HttpMethod.GET,"/test").handler(routingContext ->
                routingContext.response()
                        .setStatusCode(200).end("Test it"));

        mainRouter.route(HttpMethod.GET,"/test/:id?:name")
                .produces("APPLICATION/JSON")
                .handler(routingContext ->{
            String id = routingContext.request().getParam("id");
            String name=routingContext.request().getParam("name");
                    JsonObject object=new JsonObject();
                    object.put("id",id);
                    object.put("name",name);
            System.out.println(routingContext.request().params().entries().toString());
            routingContext.response()

                        .setStatusCode(200).end(object.toBuffer());
        });




        server.requestHandler(mainRouter::accept).listen(8080);
    }
}

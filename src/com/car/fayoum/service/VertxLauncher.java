package com.car.fayoum.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

/**
 * Created by ahmedissawi on 12/10/17.
 */
public class VertxLauncher extends AbstractVerticle {


    private final Vertx vertx = Vertx.vertx();

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        HttpServer server = vertx.createHttpServer();


        Router mainRouter = Router.router(vertx);

        mainRouter.route(HttpMethod.GET,"/test").handler(routingContext ->
                routingContext.response()
                        .setStatusCode(200).end("Test it"));


        UserService service = new UserService();

        service.registerHandler(mainRouter);

        server.requestHandler(mainRouter::accept);

        server.listen(8080, (rs -> {
            if (rs.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(rs.cause());
            }
        }));

    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        vertx.close(res -> vertx.close(stopFuture.completer()));
    }

    @Override
    public JsonObject config() {
        return super.config();
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(VertxLauncher.class.getName());
    }
}

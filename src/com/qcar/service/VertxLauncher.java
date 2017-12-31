package com.qcar.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.qcar.service.handlers.HandlerFactory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.SLF4JLogDelegateFactory;
import io.vertx.ext.web.Router;

import static io.vertx.core.logging.LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME;

/**
 * Created by ahmedissawi on 12/10/17.
 */
public class VertxLauncher extends AbstractVerticle {


    private final Vertx vertx = Vertx.vertx();


    private void initLog(){
        System.setProperty(LOGGER_DELEGATE_FACTORY_CLASS_NAME, SLF4JLogDelegateFactory.class.getName());
        LoggerContext loggerContext = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.INFO);
    }
    @Override
    public void start(Future<Void> startFuture) {



        initLog();

        HttpServer server = vertx.createHttpServer();

        Router mainRouter = Router.router(vertx);

        UserCtrl service = new UserCtrl();

        service.registerHandler(mainRouter);

        mainRouter.route().failureHandler(HandlerFactory.errorHandler());

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
    public void stop(Future<Void> stopFuture) {
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

package com.qcar.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.*;
import com.qcar.service.ctrl.CtrlFactory;

import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.config.ConfigHandler;
import com.qcar.service.handlers.config.SecurityHandler;
import com.qcar.utils.Constants;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.SLF4JLogDelegateFactory;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import  io.vertx.ext.web.handler.sockjs.BridgeOptions.*;

import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.kotlin.ext.web.handler.sockjs.PermittedOptionsKt;
import org.bson.BSONObject;
import org.bson.conversions.Bson;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.logging.slf4j.SLF4JLoggerImplFactory;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static io.vertx.core.logging.LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME;

/**
 * Created by ahmedissawi on 12/10/17.
 */
public class VertxLauncher extends AbstractVerticle {


    private final Vertx vertx = Vertx.vertx();

    private static final org.slf4j.Logger logger= LoggerFactory.getLogger(VertxLauncher.class);



    private void initLog(){
        System.setProperty(LOGGER_DELEGATE_FACTORY_CLASS_NAME, SLF4JLogDelegateFactory.class.getName());
        LoggerContext loggerContext = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.INFO);
        MorphiaLoggerFactory.registerLogger(SLF4JLoggerImplFactory.class);

        Logger morphiaLogger=loggerContext.getLogger("org.mongodb.morphia");
        morphiaLogger.setLevel(Level.INFO);
    }

    private void initCtrls(Router router){
        CtrlFactory.userCtrl().registerHandler(router);
        CtrlFactory.driverCtrl().registerHandler(router);
        CtrlFactory.customerCtrl().registerHandler(router);
        CtrlFactory.orderCtrl().registerHandler(router);
        CtrlFactory.tripCtrl().registerHandler(router);
        CtrlFactory.dashboardCtrl().registerHandler(router);

    }

    private void initSecurityHandler(Router router){
        SecurityHandler handler=HandlerFactory.securityHandler();
        router.route("/api/*").handler(handler);
    }


    private SockJSHandler sockJSHandler(){
        SockJSHandlerOptions options = new SockJSHandlerOptions().setHeartbeatInterval(2000);
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx, options);
        BridgeOptions bo = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddressRegex("auction\\.[0-9]+"));
        Map<String,Object>mp=new HashMap<>();
        mp.put("A","B");
        sockJSHandler.bridge(bo, event -> {
            if(event.type()== BridgeEventType.SOCKET_CREATED){
                logger.info("Creating Connection");



            }
            else if(event.type()==BridgeEventType.RECEIVE){
                event.setRawMessage(new JsonObject(mp));
                logger.info("Message is -->",event.getRawMessage().toString());
            }
            else if(event.type()==BridgeEventType.SEND){
                event.setRawMessage(new JsonObject(mp));
            }

            event.complete(true);
        });

        return sockJSHandler;
    }
    @Override
    public void start(Future<Void> startFuture) {


        initLog();

        HttpServer server = vertx.createHttpServer();


        Router mainRouter = Router.router(vertx);

        //mainRouter.route("/eventbus/*").handler(sockJSHandler());


        configServer(mainRouter);

        initSecurityHandler(mainRouter);

        initCtrls(mainRouter);

        mainRouter.route().failureHandler(HandlerFactory.errorHandler());

        mainRouter.route().useNormalisedPath(false);

        mainRouter.route().last().handler(HandlerFactory.resourceNotFoundHandler());

        server.requestHandler(mainRouter::accept);



        server.listen(8080, (rs -> {
            if (rs.succeeded()) {
                startFuture.complete();
            } else {
               startFuture.fail(rs.cause());
            }
        }));

    }

    private void configServer(Router router){

        checkMongoServer(vertx);
        router.route().handler(new ConfigHandler());

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.HEAD)
                .allowedHeader("X-PINGARUNER")
                .allowedHeader("Content-Type")
                .allowedHeader("Access-Control-Request-Method")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Headers")

                .allowedHeader(Constants.AUTHENTICATION_HEADER));

    }

    @Override
    public void stop(Future<Void> stopFuture) {
        vertx.close(res -> vertx.close(stopFuture.completer()));
    }

    private void checkMongoServer(Vertx vertx){

        try {
            MongoClient mongo = new MongoClient();
            Bson ping = new BasicDBObject("ping", "1");
            mongo.getDatabase(Constants.DB_NAME).runCommand(ping);
        } catch (MongoException e) {
            logger.error("Mongo Server is not working",e);
            vertx.deploymentIDs().forEach(vertx::undeploy);
            System.exit(-9);

        }
    }

    @Override
    public JsonObject config() {


        return super.config();
    }

    public static void main(String[] args) {


        DeploymentOptions options=new DeploymentOptions();
        Vertx.vertx().
                deployVerticle(VertxLauncher.class,options);
    }
}

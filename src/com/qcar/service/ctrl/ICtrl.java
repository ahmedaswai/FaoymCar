package com.qcar.service.ctrl;

import com.qcar.utils.MediaType;
import io.vertx.ext.web.Router;
import com.qcar.service.handlers.business.GenericHandler;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Created by ahmedissawi on 12/27/17.
 */
public interface ICtrl {
    default String getRoute(String path){
        return String.join("/",getRoute(),path);
    }
    String getRoute();
    void registerHandler(Router mainRouter);
    default void registerDefaultRoutes(GenericHandler handler, Router mainRouter){
       mainRouter.get()
                .path(getRoute("id/:id"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findById);


        mainRouter.get()
                .path(getRoute())
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findAll);

        mainRouter.delete()
                .path(getRoute("id/:id"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::doDelete);

        mainRouter.delete()
                .path(getRoute("bulk"))
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())
                .handler(handler::doDeleteBulk);

        mainRouter.post()
                .path(getRoute())
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())
                .handler(handler::doAdd);

        mainRouter.put()
                .path(getRoute())
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())
                .handler(handler::doAdd);



    }
}

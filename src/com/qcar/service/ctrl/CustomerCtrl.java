package com.qcar.service.ctrl;

import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.business.CustomerHandler;
import com.qcar.utils.MediaType;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class CustomerCtrl implements ICtrl {

    private final CustomerHandler handler = HandlerFactory.customerHandler();

    @Override
    public String getRoute() {
        return "/api/customers";
    }

    @Override
    public void registerHandler(Router mainRouter) {
        registerDefaultRoutes(handler, mainRouter);

        mainRouter.get()
                .path(getRoute("active"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findAllActive);


        mainRouter.put()
                .path(getRoute(":id/activate"))
                .produces(MediaType.APPLICATION_JSON)
                .handler(handler::doActivate);

        mainRouter.put()
                .path(getRoute(":id/de-activate"))
                .produces(MediaType.APPLICATION_JSON)
                .handler(handler::doDeActivate);


    }
}

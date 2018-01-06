package com.qcar.service.ctrl;

import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.business.OrderHandler;
import com.qcar.utils.MediaType;
import io.vertx.ext.web.Router;

public class OrderCtrl implements ICtrl {

    private final OrderHandler handler = HandlerFactory.orderHandler();

    @Override
    public String getRoute() {
        return "/api/orders";
    }

    @Override
    public void registerHandler(Router mainRouter) {
        registerDefaultRoutes(handler, mainRouter);

        mainRouter.get()
                .path(getRoute("active"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findAllActive);

        mainRouter.get()
                .path(getRoute("order-num/:orderNum"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findByOrderNum);

        mainRouter.get()
                .path(getRoute("criteria"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findByCriteria);


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

package com.qcar.service.ctrl;

import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.business.TripHandler;
import com.qcar.utils.MediaType;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class TripCtrl implements ICtrl{


    private TripHandler handler=HandlerFactory.tripHandler();
    @Override
    public String getRoute() {
        return "/api/trips";
    }

    @Override
    public void registerHandler(Router mainRouter) {

        registerDefaultRoutes(handler,mainRouter);

        mainRouter.get()
                .path(getRoute("trip-num/:tripNum"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findByTripNum);

        mainRouter.get()
                .path(getRoute("trip-status/:tripStatus"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findByTripStatus);
        mainRouter.get()
                .path(getRoute("criteria"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findByCriteria);
        mainRouter.put()
                .path(getRoute("trip-status"))
                .handler(BodyHandler.create())
                .produces(MediaType.APPLICATION_JSON)
                .handler(handler::changeStatus);

        mainRouter.post()
                .path(getRoute("trip-cost"))
                .handler(BodyHandler.create())
                .produces(MediaType.APPLICATION_JSON)
                .handler(handler::calcTripCost);

    }

}

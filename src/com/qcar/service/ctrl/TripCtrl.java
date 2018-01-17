package com.qcar.service.ctrl;

import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.business.TripHandler;
import io.vertx.ext.web.Router;

public class TripCtrl implements ICtrl{


    private TripHandler handler=HandlerFactory.tripHandler();
    @Override
    public String getRoute() {
        return "/api/trips";
    }

    @Override
    public void registerHandler(Router mainRouter) {

        registerDefaultRoutes(handler,mainRouter);

    }

}

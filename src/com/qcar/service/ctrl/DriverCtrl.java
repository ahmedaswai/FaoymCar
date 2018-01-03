package com.qcar.service.ctrl;

import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.business.DriverHandler;
import com.qcar.service.handlers.business.UserHandler;
import com.qcar.utils.MediaType;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class DriverCtrl implements ICtrl{
    @Override
    public String getRoute() {
        return "/api/drivers";
    }

    @Override
    public void registerHandler(Router mainRouter) {
        DriverHandler handler = HandlerFactory.driverHandler();

        mainRouter.get()
                .path(getRoute() + "/id/:id")
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findById);


        mainRouter.get()
                .path(getRoute())
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findAll);


        mainRouter.get()
                .path(getRoute()+"/distance")
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findInDistance);

        mainRouter.get()
                .path(getRoute()+"/online")
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findAllActiveOnline);

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

        mainRouter.put()
                .path(getRoute()+"/:id/activate")
                .produces(MediaType.APPLICATION_JSON)
                .handler(handler::doActivate);

        mainRouter.put()
                .path(getRoute()+"/:id/de-activate")
                .produces(MediaType.APPLICATION_JSON)
                .handler(handler::doDeActivate);

        mainRouter.delete()
                .path(getRoute()+"/id/:id")
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::doDelete);

        mainRouter.delete()
                .path(getRoute()+"/bulk")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())
                .handler(handler::doDeleteBulk);
    }
}

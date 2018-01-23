package com.qcar.service.ctrl;

import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.business.DashboardHandler;
import com.qcar.utils.MediaType;
import io.vertx.ext.web.Router;

public class DashboardCtrl  implements ICtrl{

    private final DashboardHandler handler= HandlerFactory.dashboardHandler();


    @Override
    public String getRoute() {
        return "/api/dashboard";
    }

    @Override
    public void registerHandler(Router mainRouter) {
        mainRouter.get()
                .path(getRoute("landing-page"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::fetchDashboard);
    }
}

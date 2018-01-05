package com.qcar.service.ctrl;

import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.business.DriverHandler;
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


        registerDefaultRoutes(handler,mainRouter);

        mainRouter.get()
                .path(getRoute("distance"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findInDistance);

        mainRouter.get()
                .path(getRoute("active"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findAllActive);

        mainRouter.get()
                .path(getRoute("online"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findAllActiveOnline);

        mainRouter.get()
                .path(getRoute("pic/id/:id"))
                .produces(MediaType.APPLICATION_JSON)

                .handler(handler::findPic);



        mainRouter.put()
                .path(getRoute(":id/activate"))
                .produces(MediaType.APPLICATION_JSON)
                .handler(handler::doActivate);

        mainRouter.put()
                .path(getRoute(":id/de-activate"))
                .produces(MediaType.APPLICATION_JSON)
                .handler(handler::doDeActivate);



        mainRouter.post().path(getRoute("id/:id/pic")).
                produces(MediaType.APPLICATION_JSON).
                consumes(MediaType.MULTIPART_FORM_DATA).
                handler(BodyHandler.create().
                        setUploadsDirectory("uploads").
                        setDeleteUploadedFilesOnEnd(true)).
                handler(handler::doUploadPic);

    }
}

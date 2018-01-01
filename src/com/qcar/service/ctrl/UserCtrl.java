package com.qcar.service.ctrl;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.UserDao;
import com.qcar.service.ICtrl;
import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.UserHandler;
import com.qcar.utils.MediaType;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;


/**
 * Created by ahmedissawi on 12/27/17.
 */
public class UserCtrl implements ICtrl {

    private final UserDao dao = DaoFactory.getUserDao();

     UserCtrl(){

    }
    @Override
    public void registerHandler(Router mainRouter) {

        UserHandler userHandler = HandlerFactory.userHandler();


        mainRouter.post()
                .path(getRoute() + "/login")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())

                .handler(userHandler::doLogin);

        mainRouter.post()
                .path(getRoute() + "/password/reset")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())

                .handler(userHandler::doResetPassword);

        mainRouter.get()
                .path(getRoute() + "/id/:id")
                .produces(MediaType.APPLICATION_JSON)

                .handler(userHandler::findUserById);

        mainRouter.get()
                .path(getRoute() + "/permissions")
                .produces(MediaType.APPLICATION_JSON)

                .handler(userHandler::findAllPermissions);

        mainRouter.get()
                .path(getRoute() + "/login/:loginName")
                .produces(MediaType.APPLICATION_JSON)

                .handler(userHandler::findUserByLoginName);

        mainRouter.get()
                .path(getRoute())
                .produces(MediaType.APPLICATION_JSON)

                .handler(userHandler::findAllUsers);


        mainRouter.post()
                .path(getRoute())
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())
                .handler(userHandler::doAddUser);

        mainRouter.put()
                .path(getRoute())
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())
                .handler(userHandler::doAddUser);

        mainRouter.delete()
                .path(getRoute()+"/id/:id")
                .produces(MediaType.APPLICATION_JSON)

                .handler(userHandler::doDeleteUser);

        mainRouter.delete()
                .path(getRoute()+"/bulk")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())
                .handler(userHandler::doDeleteUserBulk);

    }

    @Override
    public String getRoute() {
        return "/users";
    }
}

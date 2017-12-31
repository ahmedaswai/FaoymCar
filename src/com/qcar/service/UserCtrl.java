package com.qcar.service;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.UserDao;
import com.qcar.service.handlers.HandlerFactory;
import com.qcar.service.handlers.LoginResult;
import com.qcar.service.handlers.UserHandler;
import com.qcar.model.mongo.User;
import com.qcar.model.mongo.service.ServiceReturnSingle;
import com.qcar.model.mongo.service.exception.QCarSecurityException;
import com.qcar.utils.MediaType;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;


/**
 * Created by ahmedissawi on 12/27/17.
 */
public class UserCtrl implements IService {

    private final UserDao dao = DaoFactory.getUserDao();

    @Override
    public void registerHandler(Router mainRouter) {

        UserHandler userHandler = HandlerFactory.userHandler();


        mainRouter.post()
                .path(getRoute() + "/login")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())

                .handler(userHandler::doLogin);

        mainRouter.get()
                .path(getRoute() + "/id/:id")
                .produces(MediaType.APPLICATION_JSON)

                .handler(userHandler::findUserById);

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

    }

    @Override
    public String getRoute() {
        return "/users";
    }
}

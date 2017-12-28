package com.qcar.service;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.UserDao;
import com.qcar.handler.HandlerFactory;
import com.qcar.handler.LoginResult;
import com.qcar.handler.UserHandler;
import com.qcar.model.mongo.User;
import com.qcar.model.mongo.service.ServiceReturnSingle;
import com.qcar.model.mongo.service.exception.QCarException;
import com.qcar.model.mongo.service.exception.QCarSecurityException;
import com.qcar.utils.MediaType;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Created by ahmedissawi on 12/27/17.
 */
public class UserService implements IService {

    private final UserDao dao = DaoFactory.getUserDao();

    @Override
    public void registerHandler(Router mainRouter) {

        UserHandler userHandler = HandlerFactory.userHandler();


        mainRouter.post()
                .path(getRoute() + "/login")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())

                .handler(ctx -> {

                            JsonObject login = ctx.getBodyAsJson();

                            String password = login.getString("password");
                            String userName = login.getString("userName");

                           LoginResult rs= userHandler.doLogin(ctx,userName, password);

                           switch (rs){

                               case INVALID_USER_NAME:
                                   ctx.fail(new QCarSecurityException("Invalid User Name"));
                                   break;
                               case INVALID_PASSWORD:
                                   ctx.fail(new QCarSecurityException("Invalid Password"));
                                   break;
                               case LOGIN_SUCCESS:
                                   ctx.response()
                                           .putHeader("content-type", MediaType.APPLICATION_JSON)
                                           .setStatusCode(200).
                                           end(ServiceReturnSingle.response(true));
                                   break;
                           }


                        }
                );

        mainRouter.get()
                .path(getRoute() + "/id/:id")
                .produces(MediaType.APPLICATION_JSON)

                .handler(routingContext -> {

                            Long id = Long.parseLong(routingContext.request().getParam("id"));

                            User u = dao.findUserById(id);

                            routingContext.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                                    .setStatusCode(200).end(Json.encodeToBuffer(u));
                        }
                );

    }

    @Override
    public String getRoute() {
        return "/users";
    }
}

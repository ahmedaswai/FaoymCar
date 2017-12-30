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

                .handler(routingContext -> {

                            Long id = Long.parseLong(routingContext.request().getParam("id"));

                            User u = dao.findUserById(id);

                            routingContext.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                                    .setStatusCode(200).end(Json.encodeToBuffer(u));
                        }
                );


        mainRouter.post()
                .path(getRoute())
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .handler(BodyHandler.create())
                .handler(ctx -> {


                            User user = Json.decodeValue(ctx.getBody(), User.class);
                            Buffer rs = ServiceReturnSingle.response(dao.saveOrMerge(user));
                            ctx.response().
                                    putHeader("content-type", MediaType.APPLICATION_JSON)

                                    .setStatusCode(200).end(rs);
                        }
                );

    }

    @Override
    public String getRoute() {
        return "/users";
    }
}

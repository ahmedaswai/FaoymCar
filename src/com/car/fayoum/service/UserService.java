package com.car.fayoum.service;

import com.car.fayoum.dao.DaoFactory;
import com.car.fayoum.dao.UserDao;
import com.car.fayoum.model.mongo.User;
import com.car.fayoum.utils.MediaType;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by ahmedissawi on 12/27/17.
 */
public class UserService implements IService {

    private final UserDao dao = DaoFactory.getUserDao();

    @Override
    public void registerHandler(Router mainRouter) {


        mainRouter.get()
                .path(getRoute() + "/:login")
                .produces(MediaType.APPLICATION_JSON)

                .handler(routingContext -> {

                            String login = routingContext.request().getParam("login");

                            User u = dao.findUserByLoginName(login);


                            routingContext.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                                    .setStatusCode(200).end(Json.encodeToBuffer(u));
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

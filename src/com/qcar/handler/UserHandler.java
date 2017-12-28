package com.qcar.handler;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.UserDao;
import com.qcar.model.mongo.User;
import com.qcar.model.mongo.service.exception.QCarException;
import com.qcar.model.mongo.service.exception.QCarSecurityException;
import com.qcar.utils.SecurityUtils;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class UserHandler {



    public LoginResult doLogin(RoutingContext ctx,String user, String pass) {
        final UserDao dao = DaoFactory.getUserDao();

        Optional<User> u = dao.findUserByLoginName(user);

        if (u.isPresent()) {
            boolean status=SecurityUtils.checkPassword(pass, u.get().getPassword());
            if(!status){
                return LoginResult.INVALID_PASSWORD;
            }
        }
        else{
            return LoginResult.INVALID_USER_NAME;
        }

        return LoginResult.LOGIN_SUCCESS;


    }

}

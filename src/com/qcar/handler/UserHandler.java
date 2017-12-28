package com.qcar.handler;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.UserDao;
import com.qcar.model.mongo.User;
import com.qcar.utils.SecurityUtils;

import java.util.Optional;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class UserHandler {
    public Boolean doLogin(String user, String pass) {
        final UserDao dao = DaoFactory.getUserDao();

        Optional<User> u = dao.findUserByLoginName(user);

        if (u.isPresent()) {
            return SecurityUtils.checkPassword(pass, u.get().getPassword());
        }
        return false;
    }

}

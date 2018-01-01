package com.qcar.service.handlers;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.UserDao;
import com.qcar.model.mongo.Permission;
import com.qcar.model.mongo.User;
import com.qcar.model.service.LoginResult;
import com.qcar.model.service.ServiceReturnList;
import com.qcar.model.service.ServiceReturnMap;
import com.qcar.model.service.ServiceReturnSingle;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarSecurityException;
import com.qcar.utils.CollectionUtils;
import com.qcar.utils.JwtTokenUtil;
import com.qcar.utils.MediaType;
import com.qcar.utils.SecurityUtils;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class UserHandler extends GenericHandler{

    final UserDao dao;
    UserHandler(){
           dao = DaoFactory.getUserDao();
    }


    public void findUserByLoginName(RoutingContext ctx){

        String user =ctx.request().getParam("loginName");

        User u = dao.findUserByLoginName(user).get();

        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
    public void findAllPermissions(RoutingContext ctx){

        Buffer rs = ServiceReturnMap.response(Permission.PERMISSION_MAP);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
    public void findUserById(RoutingContext ctx){


            Long id = Long.parseLong(ctx.request().getParam("id"));

            User u = dao.findUserById(id);
            Buffer rs = ServiceReturnSingle.response(dao.saveOrMerge(u));
            ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                    .setStatusCode(200).end(rs);


    }
    public void findAllUsers(RoutingContext ctx){




        List<User> lst = dao.findAll();

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void doAddUser(RoutingContext ctx){



            User user = Json.decodeValue(ctx.getBody(), User.class);
            String hashedPassword=SecurityUtils.hashPassword(user.getPassword());
            user.password(hashedPassword);

            setClientInfo(user,ctx);
            Buffer rs = ServiceReturnSingle.response(dao.saveOrMerge(user));
            ctx.response().
                    putHeader("content-type", MediaType.APPLICATION_JSON)

                    .setStatusCode(200).end(rs);


    }
    public void doDeleteUser(RoutingContext ctx){


        Long id = Long.parseLong(ctx.request().getParam("id"));
        Buffer rs = ServiceReturnSingle.response(dao.deleteById(id));

        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void doDeleteUserBulk(RoutingContext ctx){


        List<Long> ids = Json.decodeValue(ctx.getBody(), CollectionUtils.LONG_LIST_TYPE);
        Map<Long,Boolean> mp=ids.stream().collect(Collectors.toMap(Function.identity(),dao::deleteById));
        Buffer rs = ServiceReturnMap.response(mp);

        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void doLogin(RoutingContext ctx) {
        JsonObject login = ctx.getBodyAsJson();

        String password = login.getString("password");
        String userName = login.getString("userName");

        LoginResult rs = checkLogin(userName, password);

        switch (rs) {

            case INVALID_USER_NAME:
                ctx.fail(new QCarSecurityException(ErrorCodes.USER_NOT_FOUND));
                break;
            case INVALID_PASSWORD:
                ctx.fail(new QCarSecurityException(ErrorCodes.INVALID_PASSWORD));
                break;
            case LOGIN_SUCCESS:
                Map<String,Object>mp=new HashMap<>();
                User u=dao.findUserByLoginName(userName).get();
                mp.put("user",u);
                mp.put("token", JwtTokenUtil.generateToken(u).get());
                ctx.response()
                        .putHeader("content-type", MediaType.APPLICATION_JSON)
                        .setStatusCode(200).
                        end(ServiceReturnMap.response(mp));
                break;
        }


    }
    public void doResetPassword(RoutingContext ctx){
        JsonObject login = ctx.getBodyAsJson();

        String newPassword = login.getString("newPassword");
        String oldPassword = login.getString("oldPassword");

        String userName = login.getString("userName");
        LoginResult rs= checkLogin(userName,oldPassword);
        switch (rs) {

            case INVALID_USER_NAME:
                ctx.fail(new QCarSecurityException(ErrorCodes.USER_NOT_FOUND));
                break;
            case INVALID_PASSWORD:
                ctx.fail(new QCarSecurityException(ErrorCodes.INVALID_PASSWORD));
                break;
            default:
                break;
        }

        User u= dao.findUserByLoginName(userName).get();

        String newHashedPassword=SecurityUtils.hashPassword(newPassword);

        u.password(newHashedPassword);

        setClientInfo(u,ctx);
        dao.update(u);

        ctx.response()
                .putHeader("content-type", MediaType.APPLICATION_JSON)
                .setStatusCode(200).
                end(ServiceReturnSingle.response(true));
    }
    private LoginResult checkLogin(String user, String pass) {
        final UserDao dao = DaoFactory.getUserDao();

        Optional<User> u = dao.findUserByLoginName(user);

        if (u.isPresent()) {
            boolean status = SecurityUtils.checkPassword(pass, u.get().getPassword());
            if (!status) {
                return LoginResult.INVALID_PASSWORD;
            }
        } else {
            return LoginResult.INVALID_USER_NAME;
        }

        return LoginResult.LOGIN_SUCCESS;


    }

}

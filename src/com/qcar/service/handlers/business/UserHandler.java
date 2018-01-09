package com.qcar.service.handlers.business;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.GenericDao;
import com.qcar.dao.UserDao;
import com.qcar.model.mongo.choicelist.Permission;
import com.qcar.model.mongo.entity.User;
import com.qcar.model.service.result.LoginResult;
import com.qcar.model.service.result.ServiceReturnList;
import com.qcar.model.service.result.ServiceReturnMap;
import com.qcar.model.service.result.ServiceReturnSingle;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarSecurityException;
import com.qcar.security.JwtTokenUtil;
import com.qcar.utils.MediaType;
import com.qcar.security.SecurityUtils;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class UserHandler extends GenericHandler<User> {

    final UserDao dao;

    public UserHandler(){
           dao = DaoFactory.userDao();
    }

    @Override
    public GenericDao<User> getDao() {
        return dao;
    }


    public void findByLoginName(RoutingContext ctx){

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
    public void doHashPass(RoutingContext ctx){
        String password=ctx.request().getParam("pass");
        String hashedPassword= SecurityUtils.hashPassword(password);
        Buffer rs = ServiceReturnSingle.response(hashedPassword);
        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);

    }
    public void doAdd(RoutingContext ctx){



        User user = Json.decodeValue(ctx.getBody(), User.class);
        String hashedPassword= SecurityUtils.hashPassword(user.getPassword());
        user.password(hashedPassword);

        setClientInfo(user,ctx);
        Buffer rs = ServiceReturnSingle.response(dao.saveOrMerge(user));
        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void doLogin(RoutingContext ctx) {
        JsonObject login = ctx.getBodyAsJson();

        String password = login.getString("password");
        String userName = login.getString("userName");

        Pair<User,LoginResult> rs = checkLogin(userName, password);

        switch (rs.getValue()) {

            case INVALID_USER_NAME:
                ctx.fail(new QCarSecurityException(ErrorCodes.USER_NOT_FOUND));
                break;
            case INVALID_PASSWORD:
                ctx.fail(new QCarSecurityException(ErrorCodes.INVALID_PASSWORD));
                break;
            case LOGIN_SUCCESS:
                Map<String,Object>mp=new HashMap<>();

                User u=rs.getKey();
                if(u.getStatus()==null|| !u.getStatus()){
                    ctx.fail(new QCarSecurityException(ErrorCodes.USER_NOT_ACTIVE));
                }
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
        Pair<User,LoginResult> rs= checkLogin(userName,oldPassword);
        switch (rs.getValue()) {

            case INVALID_USER_NAME:
                ctx.fail(new QCarSecurityException(ErrorCodes.USER_NOT_FOUND));
                break;
            case INVALID_PASSWORD:
                ctx.fail(new QCarSecurityException(ErrorCodes.INVALID_PASSWORD));
                break;
            default:
                break;
        }

        User u= rs.getKey();

        String newHashedPassword=SecurityUtils.hashPassword(newPassword);

        u.password(newHashedPassword);

        setClientInfo(u,ctx);
        dao.update(u);

        ctx.response()
                .putHeader("content-type", MediaType.APPLICATION_JSON)
                .setStatusCode(200).
                end(ServiceReturnSingle.response(true));
    }
    public void doActivate(RoutingContext ctx){

        Long id = Long.parseLong(ctx.request().getParam("id"));

        User u = dao.changeStatus(id,true);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
    public void doDeActivate(RoutingContext ctx){

        Long id = Long.parseLong(ctx.request().getParam("id"));

        User u = dao.changeStatus(id,false);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }

    public void findAllActive(RoutingContext ctx) {
        List<User> lst = dao.findAllActive();

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
    private Pair<User,LoginResult> checkLogin(String user, String pass) {

        Optional<User> u = dao.findUserByLoginName(user);

        if (u.isPresent()) {
            boolean status = SecurityUtils.checkPassword(pass, u.get().getPassword());
            if (!status) {
                return new Pair<>(null,LoginResult.INVALID_PASSWORD);
            }
        } else {
            return new Pair<>(null,LoginResult.INVALID_USER_NAME);
        }

        return new Pair<>(u.get(),LoginResult.LOGIN_SUCCESS);


    }

}

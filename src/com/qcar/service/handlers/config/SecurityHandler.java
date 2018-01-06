package com.qcar.service.handlers.config;

import com.qcar.model.mongo.entity.User;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarSecurityException;
import com.qcar.utils.Constants;
import com.qcar.utils.JwtTokenUtil;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class SecurityHandler implements Handler<RoutingContext>{
    public SecurityHandler(){}

    @Override
    public void handle(RoutingContext ctx) {


        try {
            String token=ctx.request().getHeader(Constants.AUTHENTICATION_HEADER);
            User u=JwtTokenUtil.validateToken(token);
            if(u!=null){

                ctx.data().put("user",u);
                ctx.next();
            }
            else{
                ctx.fail(new QCarSecurityException(ErrorCodes.INVALID_TOKEN));
            }
        }
        catch (Exception io){
            ctx.fail(new QCarSecurityException(io,ErrorCodes.INVALID_TOKEN));
        }


    }
}

package com.qcar.service.handlers;

import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarSecurityException;
import com.qcar.utils.Constants;
import com.qcar.utils.JwtTokenUtil;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class SecurityHandler implements Handler<RoutingContext>{
    SecurityHandler(){}

    @Override
    public void handle(RoutingContext o) {

        System.out.println("Routing In Security");
         String token=o.request().getHeader(Constants.AUTHENTICATION_HEADER);
         if(JwtTokenUtil.validateToken(token)){
             o.next();
         }
         o.fail(new QCarSecurityException(ErrorCodes.INVALID_TOKEN));
    }
}

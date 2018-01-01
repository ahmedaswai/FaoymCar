package com.qcar.service.handlers;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class SecurityHandler implements Handler<RoutingContext>{
    SecurityHandler(){}

    @Override
    public void handle(RoutingContext o) {

        System.out.println("Routing In Security");
        o.next();
    }
}

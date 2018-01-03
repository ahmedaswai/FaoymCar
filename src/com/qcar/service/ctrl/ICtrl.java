package com.qcar.service.ctrl;

import io.vertx.ext.web.Router;

/**
 * Created by ahmedissawi on 12/27/17.
 */
public interface ICtrl {
    String getRoute();

    void registerHandler(Router mainRouter);
}

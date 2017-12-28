package com.qcar.service;

import io.vertx.ext.web.Router;

/**
 * Created by ahmedissawi on 12/27/17.
 */
public interface IService {
    String getRoute();

    void registerHandler(Router mainRouter);
}

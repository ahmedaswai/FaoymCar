package com.qcar.handler;

import com.qcar.model.mongo.service.ServiceError;
import com.qcar.model.mongo.service.exception.QCarException;
import com.qcar.utils.MediaType;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.ErrorHandler;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class QCarErrorHandler implements ErrorHandler {


    @Override
    public void handle(RoutingContext routingContext) {
        Throwable throwable = routingContext.failure();
        if (throwable instanceof QCarException) {
            QCarException ex = (QCarException) throwable;
            routingContext.response()
                    .putHeader("content-type", MediaType.APPLICATION_JSON)
                    .setStatusCode(500).
                    end(ServiceError.response(ex.getMessage()));

        }
    }
}

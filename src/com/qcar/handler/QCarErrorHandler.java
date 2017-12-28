package com.qcar.handler;

import com.qcar.model.mongo.service.ServiceError;
import com.qcar.model.mongo.service.exception.QCarException;
import com.qcar.model.mongo.service.exception.QCarSecurityException;
import com.qcar.utils.MediaType;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.ErrorHandler;

import java.net.HttpURLConnection;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class    QCarErrorHandler implements ErrorHandler {


    @Override
    public void handle(RoutingContext ctx) {
        Throwable throwable = ctx.failure();

        int errorCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
        Exception ex=(Exception)throwable;

        if (ex instanceof QCarSecurityException) {
            ex = (QCarSecurityException) ex;
            errorCode=HttpURLConnection.HTTP_UNAUTHORIZED;

        }
        else if (ex instanceof QCarException) {
             ex = (QCarException) ex;
            errorCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
        }

        ctx.response()
                .putHeader("content-type", MediaType.APPLICATION_JSON)
                .setStatusCode(errorCode).
                end(
                ServiceError.response(ex.getMessage()));



    }
}

package com.qcar.service.handlers.config;

import com.qcar.model.service.ServiceError;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarException;
import com.qcar.utils.MediaType;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.ErrorHandler;

import java.net.HttpURLConnection;

public class ResourceNotFoundHandler implements ErrorHandler {
    @Override
    public void handle(RoutingContext ctx) {

        Buffer bf=  ServiceError.response(new QCarException( ErrorCodes.SERVER_ROUTE_ERROR),
                                    HttpURLConnection.HTTP_NOT_FOUND);
        ctx.response()
                .putHeader("content-type", MediaType.APPLICATION_JSON)
                .setStatusCode(HttpURLConnection.HTTP_NOT_FOUND).
                end(bf);
    }
}

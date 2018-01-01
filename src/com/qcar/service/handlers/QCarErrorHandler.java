package com.qcar.service.handlers;

import com.qcar.model.service.ServiceError;
import com.qcar.model.service.exception.ErrorCodes;
import com.qcar.model.service.exception.QCarException;
import com.qcar.model.service.exception.QCarSecurityException;
import com.qcar.utils.MediaType;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import io.vertx.core.buffer.Buffer;
/**
 * Created by ahmedissawi on 12/28/17.
 */
public class    QCarErrorHandler implements ErrorHandler {


     QCarErrorHandler(){}
    private final Logger logger= LoggerFactory.getLogger(QCarErrorHandler.class);

    @Override
    public void handle(RoutingContext ctx) {
        Throwable throwable = ctx.failure();

        int errorCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
        Exception ex=(Exception)throwable;

        Buffer bf;
        if (ex instanceof QCarSecurityException) {
            QCarSecurityException e = (QCarSecurityException) ex;
            errorCode=HttpURLConnection.HTTP_UNAUTHORIZED;
            bf=ServiceError.response(ex,e.getErrorCode());

        }
        else if (ex instanceof QCarException) {
            ex = (QCarException) ex;
            errorCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
            QCarException e = (QCarException) ex;
            bf=ServiceError.response(ex,e.getErrorCode());
        }
        else{

            bf=ServiceError.response(new QCarException(ex, ErrorCodes.UN_DEFINED_EXCEPTION));
        }


        logger.error("Exception",ex);

        ctx.response()
                .putHeader("content-type", MediaType.APPLICATION_JSON)
                .setStatusCode(errorCode).
                end(bf);



    }
}

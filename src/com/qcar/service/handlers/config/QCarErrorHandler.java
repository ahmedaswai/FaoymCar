package com.qcar.service.handlers.config;

import com.qcar.model.service.result.ServiceError;
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


    public QCarErrorHandler(){}
    private final Logger logger= LoggerFactory.getLogger(QCarErrorHandler.class);

    @Override
    public void handle(RoutingContext ctx) {

        Buffer bf;
        int statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;

            Throwable throwable = ctx.failure();



            Exception ex = (Exception) throwable;


            if (ex instanceof QCarSecurityException) {
                QCarSecurityException e = (QCarSecurityException) ex;
                statusCode = HttpURLConnection.HTTP_UNAUTHORIZED;
                bf = ServiceError.response(ex, e.getErrorCode(), statusCode);

            } else if (ex instanceof QCarException) {
                QCarException qCarException = (QCarException) ex;
                statusCode = HttpURLConnection.HTTP_INTERNAL_ERROR;
                bf = ServiceError.response(ex, qCarException.getErrorCode(), statusCode);
            } else {

                bf = ServiceError.response(new QCarException(ex, ErrorCodes.UN_DEFINED_EXCEPTION), statusCode);
            }


            logger.error("Exception", ex);

        ctx.response()
                .putHeader("content-type", MediaType.APPLICATION_JSON)
                .setStatusCode(statusCode).
                end(bf);



    }
}

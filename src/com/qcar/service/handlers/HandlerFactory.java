package com.qcar.service.handlers;

/**
 * Created by ahmedissawi on 12/28/17.
 */
public class HandlerFactory {

    private static transient volatile UserHandler userHandler;
    private static transient volatile QCarErrorHandler errorHandler;
    private static transient volatile SecurityHandler securityHandler;

    public static UserHandler userHandler() {

        if (userHandler == null) {
            userHandler = new UserHandler();
            System.out.println("Creating Instance of UserHandler");
        }
        return userHandler;
    }

    public static QCarErrorHandler errorHandler() {

        if (errorHandler == null) {
            errorHandler = new QCarErrorHandler();
            System.out.println("Creating Instance of UserHandler");
        }
        return errorHandler;
    }
    public static SecurityHandler securityHandler() {

        if (securityHandler == null) {
            securityHandler = new SecurityHandler();
            System.out.println("Creating Instance of SecurityHandler");
        }
        return securityHandler;
    }

}

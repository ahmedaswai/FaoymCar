package com.qcar.service.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CtrlFactory {
    private static transient volatile UserCtrl userCtrl;
    private static transient volatile DriverCtrl driverCtrl;
    private static transient volatile CustomerCtrl customerCtrl;


    private static final Logger logger= LoggerFactory.getLogger(CtrlFactory.class);

    public static UserCtrl userCtrl() {

        if (userCtrl == null) {
            userCtrl = new UserCtrl();
            logger.debug("Creating Instance of UserCtrl");
        }
        return userCtrl;
    }
    public static DriverCtrl driverCtrl() {

        if (driverCtrl == null) {
            driverCtrl = new DriverCtrl();
            logger.debug("Creating Instance of DriverCtrl");
        }
        return driverCtrl;
    }

    public static CustomerCtrl customerCtrl() {

        if (customerCtrl == null) {
            customerCtrl = new CustomerCtrl();
            logger.debug("Creating Instance of CustomerCtrl");
        }
        return customerCtrl;
    }
}

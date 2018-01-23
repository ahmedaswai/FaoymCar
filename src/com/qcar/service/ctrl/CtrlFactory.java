package com.qcar.service.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CtrlFactory {
    private static transient volatile UserCtrl userCtrl;
    private static transient volatile DriverCtrl driverCtrl;
    private static transient volatile CustomerCtrl customerCtrl;
    private static transient volatile OrderCtrl orderCtrl;
    private static transient volatile TripCtrl tripCtrl;
    private static transient volatile DashboardCtrl dashboardCtrl;



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
    public static OrderCtrl orderCtrl() {

        if (orderCtrl == null) {
            orderCtrl = new OrderCtrl();
            logger.debug("Creating Instance of OrderCtrl");
        }
        return orderCtrl;
    }
    public static TripCtrl tripCtrl() {

        if (tripCtrl == null) {
            tripCtrl = new TripCtrl();
            logger.debug("Creating Instance of TripCtrl");
        }
        return tripCtrl;
    }
    public static DashboardCtrl dashboardCtrl() {

        if (dashboardCtrl == null) {
            dashboardCtrl = new DashboardCtrl();
            logger.debug("Creating Instance of DashboardCtrl");
        }
        return dashboardCtrl;
    }
}

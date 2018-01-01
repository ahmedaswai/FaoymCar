package com.qcar.service.ctrl;

import com.qcar.service.handlers.UserHandler;

public class CtrlFactory {
    private static transient volatile UserCtrl userCtrl;
    public static UserCtrl userCtrl() {

        if (userCtrl == null) {
            userCtrl = new UserCtrl();
            System.out.println("Creating Instance of UserCtrl");
        }
        return userCtrl;
    }
}

package com.qcar.dao;

/**
 * Created by ahmedissawi on 12/26/17.
 */
public class DaoFactory {

    private static transient volatile UserDao userDao;
    private static transient volatile OrderDao orderDao;

    public static UserDao getUserDao() {

        if (userDao == null) {
            userDao = new UserDao();
            System.out.println("Creating Instace of UserDao");
        }
        return userDao;
    }

    public static OrderDao getOrderDao() {

        if (orderDao == null) {
            orderDao = new OrderDao();
            System.out.println("Creating Instance of OrderDao");
        }
        return orderDao;
    }
}

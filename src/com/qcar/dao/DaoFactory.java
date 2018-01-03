package com.qcar.dao;

import com.qcar.service.cache.QCarCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ahmedissawi on 12/26/17.
 */
public class DaoFactory {

    private static transient volatile UserDao userDao;
    private static transient volatile DriverDao driverDao;
    private static final QCarCache qCarCache=QCarCache.instance();

    private static final Logger logger= LoggerFactory.getLogger(DaoFactory.class);
    public static UserDao userDao() {

        if (userDao == null) {
            userDao = new UserDao(qCarCache);
            userDao.populateCache();
            logger.debug("Creating Instance of UserDao");
        }
        return userDao;
    }


    public static DriverDao driverDao() {

        if (driverDao == null) {
            driverDao = new DriverDao(qCarCache);
            driverDao.populateCache();
            logger.debug("Creating Instance of DriverDao");
        }
        return driverDao;
    }
}

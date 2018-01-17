package com.qcar.service.handlers.business;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.GenericDao;
import com.qcar.dao.TripDao;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.mongo.entity.Trip;

public class TripHandler extends GenericHandler<Trip> {

    private final TripDao dao= DaoFactory.tripDao();
    @Override
    public GenericDao<Trip> getDao() {
        return dao;
    }

}

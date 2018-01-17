package com.qcar.dao;

import com.qcar.model.mongo.entity.Trip;
import com.qcar.service.cache.QCarCache;

import java.util.List;

public class TripDao  extends GenericDao<Trip> implements IDao<Trip> {



    protected TripDao(QCarCache cache) {
        super(cache);
    }

    @Override
    public Class<Trip> getEntityClass() {
        return Trip.class;
    }

    @Override
    public Trip getEntity() {
        return new Trip();
    }

    @Override
    public List<Trip> findByExample(Trip order) {
        return null;
    }
}

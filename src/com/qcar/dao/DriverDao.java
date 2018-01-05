package com.qcar.dao;

import com.qcar.model.mongo.Driver;
import com.qcar.model.mongo.FileStore;
import com.qcar.model.mongo.Location;
import com.qcar.model.mongo.User;
import com.qcar.service.cache.QCarCache;
import org.mongodb.morphia.aggregation.GeoNear;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;
import org.mongodb.morphia.query.WhereCriteria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DriverDao extends GenericDao<Driver>implements IDao<Driver>,IStatusDao{
    public DriverDao(QCarCache cache) {
        super(cache);
    }

    public static final String CURRENT_LOCATION="currentLocation";
    public static final String STATUS="status";
    public static final String ONLINE="online";
    public static final String PIC="pic";
    @Override
    public List<Driver> findByExample(Driver driver) {
        return null;
    }


    public List<Driver>findInDistance(Location loc,Double meters){


        Double metersToRadians=(meters/1000)/6378.1;
        Query<Driver>q= getDataStore().createQuery(Driver.class).
               field(CURRENT_LOCATION).
                near(loc.getLng(),loc.getLat(),metersToRadians,true).
                order(Sort.descending(ONLINE));

       return q.asList();
    }
    public Driver changeStatus(Long id,Boolean status){

        return (Driver) changeStatus(this,id,status,Driver.class);
    }

    @Override
    public Class<Driver> getEntityClass() {
        return Driver.class;
    }


    public List<Driver> findAllActiveOnline() {
        return getDataStore().createQuery(Driver.class).
                    field(STATUS).equal(true).field(ONLINE).
                equal(true).asList();
    }

    public List<Driver> findAllActive() {
        return findAllActive(this, Driver.class);
    }
    public FileStore findDriverPic(Long id) {
        return getDataStore().createQuery(Driver.class).
                field(ID).equal(id).
                project(PIC, true).
                get().getPic();
    }

    @Override
    public Driver getEntity() {
        return Driver.instance();
    }
}

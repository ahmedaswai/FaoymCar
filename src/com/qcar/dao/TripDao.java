package com.qcar.dao;

import com.qcar.model.mongo.choicelist.TripStatus;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.mongo.entity.Trip;
import com.qcar.service.cache.QCarCache;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;
import java.util.Optional;

public class TripDao  extends GenericDao<Trip> implements IDao<Trip> {


    public static final String TRIP_NUMBER="tripNumber";
    public static final String TRIP_STATUS="status";
    public static final String ACTUAL_START_TIME="actualStartTime";



    @Override
    public void prepareAdd(Trip trip){
        Long tripNumber=getUniqueSerial(trip);
        trip.tripNumber(tripNumber);

    }
    protected TripDao(QCarCache cache) {
        super(cache);
    }

    public Optional<Trip> findByTripNumber(Long tripNumber) {

        Trip t = getDataStore().
                createQuery(getEntityClass()).field(TRIP_NUMBER).
                equal(tripNumber).get();

        return Optional.ofNullable(t);
    }
    public Optional<Trip> changeStatus(Long id,TripStatus status){
        Query<Trip> query = getDataStore().
                createQuery(Trip.class).field(GenericDao.ID).
                equal(id);

        UpdateOperations<Trip> updateOperations=getDataStore(). createUpdateOperations(Trip.class).set(TRIP_STATUS,status);
        Trip t= getDataStore().findAndModify(query,updateOperations);
        return Optional.ofNullable(t);
    }
    public List<Trip> findByTripStatus(TripStatus tripStatus) {

        List<Trip> tripList = getDataStore().
                createQuery(getEntityClass()).field(TRIP_STATUS).
                equal(tripStatus).asList();

        return tripList;
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

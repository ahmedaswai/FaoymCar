package com.qcar.dao;

import com.qcar.model.mongo.choicelist.PaymentMethod;
import com.qcar.model.mongo.choicelist.TripStatus;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.mongo.entity.Trip;
import com.qcar.model.mongo.search.OrderSearchCriteria;
import com.qcar.model.mongo.search.TripSearchCriteria;
import com.qcar.service.cache.QCarCache;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class TripDao  extends GenericDao<Trip> implements IDao<Trip> {


    public static final String TRIP_NUMBER="tripNumber";
    public static final String TRIP_STATUS="status";
    public static final String TRIP_DATE="actualStartTime";

    public static final String ACTUAL_START_TIME="actualStartTime";
    public static final String ORDER_NUM="orderNum";
    public static final String NOTES="notes";
    public static final String PAYMENT_METHOD="paymentMethod";
    public static final String CUSTOMER_ID="order.$customer.$id";
    public static final String FROM_LOCATION="fromLocation";
    public static final String ORDER_LOCATION="orderLocation";
    public static final String TO_LOCATION="toLocation";
    public static final String ORDER_DATE="orderTime";
    public static final String STATUS="status";
    public static final String DRIVER_ID="driver.$id";


    private static final Logger logger= LoggerFactory.getLogger(TripDao.class);

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
    public List<Trip>findByCriteria(TripSearchCriteria criteria){

        Query<Trip> query=getDataStore().createQuery(Trip.class).disableValidation();


        if(criteria.getOrderNum()!=null&&criteria.getOrderNum()>0){
            query.and(query.criteria(ORDER_NUM).equal(criteria.getOrderNum()));
        }

        if(criteria.getTripNotes()!=null&&!criteria.getTripNotes().isEmpty()){
            Pattern pattern=Pattern.compile(criteria.getTripNotes());
            Criteria notes=query.criteria(NOTES).equal(pattern);
            query.and(notes);
        }
        if(criteria.getStatus()!=null){
            query.and(query.criteria(STATUS).equal(criteria.getStatus()));
        }

        if(criteria.getCustomerId()!=null){
            query.and(query.criteria(CUSTOMER_ID).equal(criteria.getCustomerId()));
        }
        if(criteria.getDriverId()!=null){
            query.and(query.criteria(DRIVER_ID).equal(criteria.getDriverId()));

        }
       /*
        if(criteria.getToLocation()!=null){
            query.and(query.criteria(TO_LOCATION).near(criteria.getToLocation().getLng(),
                    criteria.getToLocation().getLat(),criteria.getToLocation().getRadius(),true));
        }
        if(criteria.getOrderLocation()!=null){
            query.and(query.criteria(ORDER_LOCATION).near(criteria.getOrderLocation().getLng(),
                    criteria.getOrderLocation().getLat(),criteria.getOrderLocation().getRadius(),true));
        }*/
        if(criteria.getTripFromStartDate()!=null&&criteria.getTripToStartDate()!=null){
            query.and(
                    query.criteria(TRIP_DATE).greaterThanOrEq(criteria.getTripFromStartDate())
                            .and(
                                    query.criteria(TRIP_DATE).lessThan(criteria.getTripToStartDate()
                                    )

                            ));
        }

        if(criteria.getTripFromStartDate()!=null&&criteria.getTripFromStartDate()==null){
            query.and(query.criteria(TRIP_DATE).greaterThanOrEq(criteria.getTripFromStartDate()));

        }
        if(criteria.getTripToStartDate()!=null&&criteria.getTripToStartDate()==null){
            query.and(query.criteria(TRIP_DATE).greaterThanOrEq(criteria.getTripToStartDate()));

        }


        logger.debug("Query is {} {} ",getEntity().getCollectionName(),query.getQueryObject());
        return query.asList();

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

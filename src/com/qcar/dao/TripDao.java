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

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

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
    public List<Trip>findByCriteria(TripSearchCriteria criteria){

        Query<Trip> query=getDataStore().createQuery(Trip.class).disableValidation();

        /*
        if(criteria.getOrderNum()!=null&&criteria.getOrderNum()>0){
            query.and(query.criteria(ORDER_NUM).equal(criteria.getOrderNum()));
        }

        if(criteria.getOrderNotes()!=null&&!criteria.getOrderNotes().isEmpty()){
            Pattern pattern=Pattern.compile(criteria.getOrderNotes());
            Criteria notes=query.criteria(NOTES).equal(pattern);
            query.and(notes);
        }
        if(criteria.getStatus()!=null){
            query.and(query.criteria(STATUS).equal(criteria.getStatus()));
        }
        if(criteria.getPaymentMethod()!=null&&criteria.getPaymentMethod()!= PaymentMethod.NONE){
            query.and(query.criteria(PAYMENT_METHOD).equal(criteria.getPaymentMethod()));
        }
        if(criteria.getCustomerId()!=null){
            query.and(query.criteria(CUSTOMER_ID).equal(criteria.getCustomerId()));
        }
        if(criteria.getFromLocation()!=null){
            query.and(query.criteria(FROM_LOCATION).near(criteria.getFromLocation().getLng(),
                    criteria.getFromLocation().getLat(),criteria.getFromLocation().getRadius(),true));
        }
        if(criteria.getToLocation()!=null){
            query.and(query.criteria(TO_LOCATION).near(criteria.getToLocation().getLng(),
                    criteria.getToLocation().getLat(),criteria.getToLocation().getRadius(),true));
        }
        if(criteria.getOrderLocation()!=null){
            query.and(query.criteria(ORDER_LOCATION).near(criteria.getOrderLocation().getLng(),
                    criteria.getOrderLocation().getLat(),criteria.getOrderLocation().getRadius(),true));
        }
        if(criteria.getFromOrderDate()!=null&&criteria.getToOrderDate()!=null){
            query.and(
                    query.criteria(ORDER_DATE).greaterThanOrEq(criteria.getFromOrderDate())
                            .and(
                                    query.criteria(ORDER_DATE).lessThan(criteria.getToOrderDate()
                                    )

                            ));
        }
        if(criteria.getFromOrderDate()!=null&&criteria.getToOrderDate()==null){
            query.and(query.criteria(ORDER_DATE).greaterThanOrEq(criteria.getFromOrderDate()));

        }
        if(criteria.getFromOrderDate()==null&&criteria.getToOrderDate()!=null){
            query.and(query.criteria(ORDER_DATE).lessThanOrEq(criteria.getToOrderDate()));
            */
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

package com.qcar.dao;

import com.qcar.model.mongo.choicelist.PaymentMethod;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.mongo.search.OrderSearchCriteria;
import com.qcar.service.cache.QCarCache;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class OrderDao extends GenericDao<Order> implements IDao<Order>, IStatusDao{

    public static final String ORDER_NUM="orderNum";
    public static final String NOTES="notes";
    public static final String PAYMENT_METHOD="paymentMethod";
    public static final String CUSTOMER_ID="customer.$id";
    public static final String FROM_LOCATION="fromLocation";
    public static final String ORDER_LOCATION="orderLocation";
    public static final String TO_LOCATION="toLocation";
    public static final String ORDER_DATE="orderTime";


    protected OrderDao(QCarCache cache) {
        super(cache);
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    public Order getEntity() {
        return Order.instance();
    }

    @Override
    public List<Order> findByExample(Order order) {
        return null;
    }
    public Order changeStatus(Long id, Boolean status) {

        return (Order) changeStatus(this, id, status, Order.class);
    }

    public Optional<Order> findByOrderNumber(Long orderId) {

        Order t = getDataStore().
                createQuery(getEntityClass()).field(ORDER_NUM).
                equal(orderId).get();

        return Optional.ofNullable(t);
    }
    @Override
    public Order save(Order order) {
        if (order != null) {
            setId(order);
            setOrderId(order);
            getDataStore().save(order);

        }
        return order;
    }

    private void setOrderId(Order order){
        Integer year= LocalDateTime.now().getYear();
        String id=order.getId().toString();
        StringBuilder idbt=new StringBuilder();
        for(int count=id.length();count<6;count++){
            idbt.append(0);
        }
        String orderNum=String.join("",year.toString(),idbt,id);
        order.orderNum(Long.parseLong(orderNum));
    }
    public List<Order> findAllActive() {

        return findAllActive(this, Order.class);
    }

    public List<Order>findByCriteria(OrderSearchCriteria criteria){

        Query<Order> query=getDataStore().createQuery(Order.class).disableValidation();

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

        }


        System.out.println(query.getQueryObject().toString());
        return query.asList();

    }

}

package com.qcar.model.mongo.search;

import com.qcar.model.mongo.choicelist.PaymentMethod;
import com.qcar.model.mongo.embedded.Location;
import com.qcar.utils.CollectionUtils;
import io.vertx.core.MultiMap;

import java.util.Date;

public class OrderSearchCriteria {

    private OrderSearchCriteria(){

    }
    private Long orderNum;
    private Date fromOrderDate;
    private Date toOrderDate;
    private String orderNotes;
    private Long customerId;


    private Location orderLocation;

    private Location fromLocation;

    private Location toLocation;

    private Boolean status;
    private PaymentMethod paymentMethod;

    public static OrderSearchCriteria instance(MultiMap mp){

        Long orderNum= CollectionUtils.getParamValue(mp,"orderNum",Long.class);
        Boolean status=CollectionUtils.getParamValue(mp,"status",Boolean.class);
        PaymentMethod paymentMethod= CollectionUtils. getParamValue(mp,"paymentMethod",PaymentMethod.class);
        Long cusId=CollectionUtils. getParamValue(mp,"customerId",Long.class);
        String orderNotes=CollectionUtils. getParamValue(mp,"notes",String.class);
        Location orderLocation=CollectionUtils. getParamValue(mp,"order",Location.class);
        Location fromLocation=CollectionUtils. getParamValue(mp,"from",Location.class);
        Location toLocation=CollectionUtils. getParamValue(mp,"to",Location.class);
        Date fromOrderDate=CollectionUtils.getParamValue(mp,"orderDateFrom",Date.class);
        Date toOrderDate=CollectionUtils.getParamValue(mp,"orderDateTo",Date.class);

        return new OrderSearchCriteria().
                orderNum(orderNum).
                status(status).
                paymentMethod(paymentMethod).
                customerId(cusId).
                orderNotes(orderNotes).
                orderLocation(orderLocation).
                fromLocation(fromLocation).
                toLocation(toLocation).
                fromOrderDate(fromOrderDate).
                toOrderDate(toOrderDate);
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public OrderSearchCriteria orderNum(Long orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public Date getFromOrderDate() {
        return fromOrderDate;
    }

    public OrderSearchCriteria fromOrderDate(Date fromOrderDate) {
        this.fromOrderDate = fromOrderDate;
        return this;
    }

    public Date getToOrderDate() {
        return toOrderDate;
    }

    public OrderSearchCriteria toOrderDate(Date toOrderDate) {
        this.toOrderDate = toOrderDate;
        return this;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public OrderSearchCriteria orderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public OrderSearchCriteria customerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }


    public Location getFromLocation() {
        return fromLocation;
    }

    public OrderSearchCriteria fromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
        return this;
    }


    public Location getToLocation() {
        return toLocation;
    }

    public OrderSearchCriteria toLocation(Location toLocation) {
        this.toLocation = toLocation;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public OrderSearchCriteria status(Boolean status) {
        this.status = status;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public OrderSearchCriteria paymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Location getOrderLocation() {
        return orderLocation;
    }

    public OrderSearchCriteria orderLocation(Location orderLocation) {
        this.orderLocation = orderLocation;
        return this;
    }


}

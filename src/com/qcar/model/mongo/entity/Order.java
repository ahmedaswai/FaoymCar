package com.qcar.model.mongo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qcar.model.mongo.embedded.Location;
import com.qcar.model.mongo.choicelist.PaymentMethod;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */


@Entity(value = "orders", noClassnameStored = true)
@Indexes(
        {       @Index(value = "id", fields = @Field("id")),
                @Index(value = "orderNum", fields = @Field( value = "orderNum",type = IndexType.DESC)),
                @Index(value = "fromLocation", fields = @Field(value = "fromLocation", type = IndexType.GEO2DSPHERE)),
                @Index(value = "orderLocation", fields = @Field(value = "orderLocation", type = IndexType.GEO2DSPHERE)),
                @Index(value = "toLocation", fields = @Field(value = "toLocation", type = IndexType.GEO2DSPHERE)),
                @Index(value = "orderTime", fields = @Field(value = "orderTime", type = IndexType.DESC))

        }
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order extends GenericEntity {
    private Long orderNum;
    private Location orderLocation;
    private Location fromLocation;
    private Location toLocation;

    private Date orderTime;
    private Date tripStartTime;
    private Date tripEndTime;

    private String notes;

    private PaymentMethod paymentMethod;
    private Boolean status;

    @Reference
    private Customer customer;

    public Location getOrderLocation() {
        return orderLocation;
    }

    public Order orderLocation(Location orderLocation) {
        this.orderLocation = orderLocation;
        return this;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public Order fromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
        return this;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public Order toLocation(Location toLocation) {
        this.toLocation = toLocation;
        return this;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public Order orderTime(Date orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public Date getTripStartTime() {
        return tripStartTime;
    }

    public Order tripStartTime(Date tripStartTime) {
        this.tripStartTime = tripStartTime;
        return this;
    }

    public Date getTripEndTime() {
        return tripEndTime;
    }

    public Order tripEndTime(Date tripEndTime) {
        this.tripEndTime = tripEndTime;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Order status(Boolean status) {
        this.status = status;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public Order notes(String notes) {
        this.notes = notes;
        return this;
    }
    public Customer getCustomer() {
        return customer;
    }

    public Order customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Order paymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }
    public static final Order instance(){
        return new Order();
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public Order orderNum(Long orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    @JsonIgnore
    public Boolean isCached(){
        return false;
    }
    @Override
    @JsonIgnore
    public String getCollectionName() {
        return "orders";
    }
}

package com.car.fayoum.model.mongo;

import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */

@Entity("trips")
@Indexes(
        {@Index(value = "id", fields =@Field("id")),
                @Index(value = "startLoc", fields =@Field(value = "startLoc",type = IndexType.GEO2DSPHERE))
        }
)
public class Trip extends GenericEntity{
    private Location startLoc;
    private Location endLoc;
    @Reference
    private Customer customer;

    @Reference
    private Driver driver;

    @Reference
    private Order order;


    private Integer status;
    private Date propsedStartTime;
    private Date propsedEndTime;
    private Double propsedDistance;
    private Double propsedCost;
    private Date actualStartTime;
    private Date actualEndTime;
    private Double actualCost;
    private Double actualDistance;
    private String notes;

    public Location getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(Location startLoc) {
        this.startLoc = startLoc;
    }

    public Location getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(Location endLoc) {
        this.endLoc = endLoc;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPropsedStartTime() {
        return propsedStartTime;
    }

    public void setPropsedStartTime(Date propsedStartTime) {
        this.propsedStartTime = propsedStartTime;
    }

    public Date getPropsedEndTime() {
        return propsedEndTime;
    }

    public void setPropsedEndTime(Date propsedEndTime) {
        this.propsedEndTime = propsedEndTime;
    }

    public Double getPropsedDistance() {
        return propsedDistance;
    }

    public void setPropsedDistance(Double propsedDistance) {
        this.propsedDistance = propsedDistance;
    }

    public Double getPropsedCost() {
        return propsedCost;
    }

    public void setPropsedCost(Double propsedCost) {
        this.propsedCost = propsedCost;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public Double getActualCost() {
        return actualCost;
    }

    public void setActualCost(Double actualCost) {
        this.actualCost = actualCost;
    }

    public Double getActualDistance() {
        return actualDistance;
    }

    public void setActualDistance(Double actualDistance) {
        this.actualDistance = actualDistance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getCollectionName() {
        return "trips";
    }
}

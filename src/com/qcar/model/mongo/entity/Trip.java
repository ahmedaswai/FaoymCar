package com.qcar.model.mongo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qcar.model.mongo.embedded.Location;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */

@Entity(value = "trips",noClassnameStored = true)
@Indexes(
                {@Index(value = "id", fields = @Field("id")),
                @Index(value = "startLoc", fields = @Field(value = "startLoc", type = IndexType.GEO2DSPHERE)),
                @Index(value = "endLoc", fields = @Field(value = "endLoc", type = IndexType.GEO2DSPHERE)),
                 @Index(value = "actualStartTime", fields = @Field(value = "actualStartTime",type =IndexType.DESC)),
                 @Index(value = "actualEndTime", fields = @Field(value = "actualEndTime",type =IndexType.DESC))
                }


)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Trip extends GenericEntity {

    private Location startLoc;
    private Location endLoc;


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

    public Trip startLoc(Location startLoc) {
        this.startLoc = startLoc;
        return this;
    }

    public Location getEndLoc() {
        return endLoc;
    }

    public Trip endLoc(Location endLoc) {
        this.endLoc = endLoc;
        return this;
    }

    public Driver getDriver() {
        return driver;
    }

    public Trip driver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Trip order(Order order) {
        this.order = order;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Trip satus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getPropsedStartTime() {
        return propsedStartTime;
    }

    public Trip propsedStartTime(Date propsedStartTime) {
        this.propsedStartTime = propsedStartTime;
        return this;
    }

    public Date getPropsedEndTime() {
        return propsedEndTime;
    }

    public Trip propsedEndTime(Date propsedEndTime) {
        this.propsedEndTime = propsedEndTime;
        return this;
    }

    public Double getPropsedDistance() {
        return propsedDistance;
    }

    public Trip propsedDistance(Double propsedDistance) {
        this.propsedDistance = propsedDistance;
        return this;
    }

    public Double getPropsedCost() {
        return propsedCost;
    }

    public Trip propsedCost(Double propsedCost) {
        this.propsedCost = propsedCost;
        return this;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public Trip actualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
        return this;
    }

    public Date getActualEndTime() {
        return actualEndTime;
    }

    public Trip actualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
        return this;
    }

    public Double getActualCost() {
        return actualCost;
    }

    public Trip actualCost(Double actualCost) {
        this.actualCost = actualCost;
        return this;
    }

    public Double getActualDistance() {
        return actualDistance;
    }

    public Trip actualDistance(Double actualDistance) {
        this.actualDistance = actualDistance;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Trip notes(String notes) {
        this.notes = notes;
        return this;
    }
    public static final Trip instance(){
        return new Trip();
    }

    @JsonIgnore
    public Boolean isCached(){
        return false;
    }
    @Override
    @JsonIgnore
    public String getCollectionName() {
        return "trips";
    }
}

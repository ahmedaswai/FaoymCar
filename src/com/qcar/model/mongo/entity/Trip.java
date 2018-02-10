package com.qcar.model.mongo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qcar.model.mongo.choicelist.TripStatus;
import com.qcar.model.mongo.embedded.Location;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */

@Entity(value = "trips",noClassnameStored = true)
@Indexes(
                {
                 @Index(value = "id", fields = @Field("id")),

                @Index(value = "tripNumber",options = @IndexOptions(unique = true), fields = @Field(value = "tripNumber",type = IndexType.DESC)),
                @Index(value = "startLoc", fields = @Field(value = "startLoc", type = IndexType.GEO2DSPHERE)),
                @Index(value = "endLoc", fields = @Field(value = "endLoc", type = IndexType.GEO2DSPHERE)),
                 @Index(value = "actualStartTime", fields = @Field(value = "actualStartTime",type =IndexType.DESC)),
                 @Index(value = "actualEndTime", fields = @Field(value = "actualEndTime",type =IndexType.DESC))
                }


)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Trip extends GenericEntity implements Serializable {

    private Location startLoc;
    private Location endLoc;

    @Reference
    private Driver driver;

    @Reference
    private Order order;


    private TripStatus status;
    private Date proposedStartTime;
    private Date proposedEndTime;
    private Double proposedDistance;
    private Double proposedCost;

    private Date actualStartTime;
    private Date actualEndTime;
    private Double actualCost;
    private Double actualDistance;
    private String notes;
    private Long tripNumber;
    // waiting time in hours
    private Integer waitingTime=0;

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

    public TripStatus getStatus() {
        return status;
    }

    public Trip satus(TripStatus status) {
        this.status = status;
        return this;
    }

    public Date getProposedStartTime() {
        return proposedStartTime;
    }

    public Trip proposedStartTime(Date proposedStartTime) {
        this.proposedStartTime = proposedStartTime;
        return this;
    }

    public Date getProposedEndTime() {
        return proposedEndTime;
    }

    public Trip proposedEndTime(Date proposedEndTime) {
        this.proposedEndTime = proposedEndTime;
        return this;
    }

    public Double getProposedDistance() {
        return proposedDistance;
    }

    public Trip proposedDistance(Double proposedDistance) {
        this.proposedDistance = proposedDistance;
        return this;
    }

    public Double getProposedCost() {
        return proposedCost;
    }

    public Trip proposedCost(Double proposedCost) {
        this.proposedCost = proposedCost;
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

    public Long getTripNumber() {
        return tripNumber;
    }

    public Trip tripNumber(Long tripNumber) {
        this.tripNumber = tripNumber;
        return this;
    }
    public Integer getWaitingTime() {
        return waitingTime;
    }

    public Trip waitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
        return this;
    }

    @Override
    @JsonIgnore
    public String getCollectionName() {
        return "trips";
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Trip{");
        sb.append("startLoc=").append(startLoc);
        sb.append(", endLoc=").append(endLoc);
        sb.append(", driver=").append(driver);
        sb.append(", order=").append(order);
        sb.append(", status=").append(status);
        sb.append(", proposedStartTime=").append(proposedStartTime);
        sb.append(", proposedEndTime=").append(proposedEndTime);
        sb.append(", proposedDistance=").append(proposedDistance);
        sb.append(", proposedCost=").append(proposedCost);
        sb.append(", actualStartTime=").append(actualStartTime);
        sb.append(", actualEndTime=").append(actualEndTime);
        sb.append(", actualCost=").append(actualCost);
        sb.append(", actualDistance=").append(actualDistance);
        sb.append(", notes='").append(notes).append('\'');
        sb.append(", tripNumber=").append(tripNumber);
        sb.append(", waitingTime=").append(waitingTime);
        sb.append('}');
        return sb.toString();
    }
}

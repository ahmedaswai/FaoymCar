package com.qcar.model.mongo.search;

import com.qcar.model.mongo.choicelist.TripStatus;
import com.qcar.model.mongo.embedded.Location;
import com.qcar.utils.CollectionUtils;
import io.vertx.core.MultiMap;

import java.util.Date;


public class TripSearchCriteria {

    private Long orderNum;
    private Long tripNum;
    private Long customerId;
    private String tripNotes;
    private Long driverId;

    private Location orderTripLocation;
    private Location startTripLocation;
    private Location endTripLocation;

    private Date tripFromStartDate;
    private Date tripToStartDate;

    private Double fromDistance;
    private Double toDistance;
    private Double fromCost;
    private Double toCost;

    private Long fromWaitingTime;
    private Long toWaitingTime;

    private TripStatus status;

    private TripSearchCriteria(){}

    public static TripSearchCriteria instance(MultiMap mp){
        Long orderNum= CollectionUtils.getParamValue(mp,"orderNum",Long.class);
        Long tripNumber= CollectionUtils.getParamValue(mp,"tripNumber",Long.class);

        TripStatus tripStatus= CollectionUtils. getParamValue(mp,"tripStatus",TripStatus.class);
        Long customerId=CollectionUtils. getParamValue(mp,"customerId",Long.class);
        Long driverId=CollectionUtils. getParamValue(mp,"driverId",Long.class);

        String tripNotes=CollectionUtils. getParamValue(mp,"tripNotes",String.class);

        Double fromDistance=CollectionUtils. getParamValue(mp,"fromDistance",Double.class);
        Double toDistance=CollectionUtils. getParamValue(mp,"toDistance",Double.class);

        Double fromCost=CollectionUtils. getParamValue(mp,"fromCost",Double.class);
        Double toCost=CollectionUtils. getParamValue(mp,"toCost",Double.class);

        Location orderLocation=CollectionUtils. getParamValue(mp,"orderTripLocation",Location.class);
        Location fromLocation=CollectionUtils. getParamValue(mp,"startTripLocation",Location.class);
        Location toLocation=CollectionUtils. getParamValue(mp,"endTripLocation",Location.class);

        Date tripFromStartDate=CollectionUtils.getParamValue(mp,"tripFromStartDate",Date.class);
        Date tripToStartDate=CollectionUtils.getParamValue(mp,"tripToStartDate",Date.class);

        Long fromWaitingTime=CollectionUtils.getParamValue(mp,"fromWaitingTime",Long.class);
        Long toWaitingTime=CollectionUtils.getParamValue(mp,"toWaitingTime",Long.class);

        return new TripSearchCriteria()
                .customerId(customerId)
                .driverId(driverId)
                .orderNum(orderNum)
                .fromCost(fromCost)
                .tripNum(tripNumber)
                .toCost(toCost)
                .fromWaitingTime(fromWaitingTime)
                .toWaitingTime(toWaitingTime)
                .status(tripStatus)
                .fromDistance(fromDistance)
                .toDistance(toDistance)
                .tripFromStartDate(tripFromStartDate)
                .tripToStartDate(tripToStartDate)
                .tripNotes(tripNotes)
                .orderTripLocation(orderLocation)
                .startTripLocation(fromLocation)
                .endTripLocation(toLocation)
                ;

    }



    public Long getTripNum() {
        return tripNum;
    }

    public TripSearchCriteria tripNum(Long tripNum) {
        this.tripNum = tripNum;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public TripSearchCriteria customerId(Long cusId) {
        this.customerId = cusId;
        return this;
    }

    public Location getOrderTripLocation() {
        return orderTripLocation;
    }

    public TripSearchCriteria orderTripLocation(Location orderTripLocation) {
        this.orderTripLocation = orderTripLocation;
        return this;
    }

    public Location getStartTripLocation() {
        return startTripLocation;
    }

    public TripSearchCriteria startTripLocation(Location startTripLocation) {
        this.startTripLocation = startTripLocation;
        return this;
    }

    public Location getEndTripLocation() {
        return endTripLocation;
    }

    public TripSearchCriteria endTripLocation(Location endTripLocation) {
        this.endTripLocation = endTripLocation;
        return this;
    }

    public Long getDriverId() {
        return driverId;
    }

    public TripSearchCriteria driverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public Double getFromDistance() {
        return fromDistance;
    }

    public TripSearchCriteria fromDistance(Double fromDistance) {
        this.fromDistance = fromDistance;
        return this;
    }

    public Double getToDistance() {
        return toDistance;
    }

    public TripSearchCriteria toDistance(Double toDistance) {
        this.toDistance = toDistance;
        return this;
    }

    public Double getFromCost() {
        return fromCost;
    }

    public TripSearchCriteria fromCost(Double fromCost) {
        this.fromCost = fromCost;
        return this;
    }

    public Double getToCost() {
        return toCost;
    }

    public TripSearchCriteria toCost(Double toCost) {
        this.toCost = toCost;
        return this;
    }

    public Long getFromWaitingTime() {
        return fromWaitingTime;
    }

    public TripSearchCriteria fromWaitingTime(Long fromWaitingTime) {
        this.fromWaitingTime = fromWaitingTime;
        return this;
    }

    public Long getToWaitingTime() {
        return toWaitingTime;
    }

    public TripSearchCriteria toWaitingTime(Long toWaitingTime) {
        this.toWaitingTime = toWaitingTime;
        return this;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public TripSearchCriteria orderNum(Long orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public String getTripNotes() {
        return tripNotes;
    }

    public TripSearchCriteria tripNotes(String tripNotes) {
        this.tripNotes = tripNotes;
        return this;
    }

    public Date getTripFromStartDate() {
        return tripFromStartDate;
    }

    public TripSearchCriteria tripFromStartDate(Date d) {
        this.tripFromStartDate=d;
        return this;
    }

    public TripSearchCriteria tripToStartDate(Date d) {
        this.tripToStartDate(d);
        return this;
    }
    public Date getTripToStartDate() {
        return tripToStartDate;
    }

    public TripStatus getStatus() {
        return status;
    }
    public TripSearchCriteria status(TripStatus status) {
       this.status=status;
       return this;
    }

}

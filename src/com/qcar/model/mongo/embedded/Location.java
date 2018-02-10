package com.qcar.model.mongo.embedded;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.MultiMap;

import java.io.Serializable;

/**
 * Created by ahmedissawi on 12/6/17.
 */
public class    Location implements Serializable {

    private Long range;

    private Double[] coordinates = new Double[2];

    private String type = "Point";

    public Location() {

    }
    public static final Location instance(MultiMap mp,String locKey){
        double lng=Double.parseDouble(mp.get(locKey+"Lng"));
        double lat=Double.parseDouble(mp.get(locKey+"Lat"));
        long range=Long.parseLong(mp.get(locKey+"Range"));

       return new Location()
               .coordinates(new Double[]{lng,lat})
               .range(range);

    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public Location coordinates(Double[] coordinates) {

        this.coordinates = coordinates;
        return this;
    }


    public String getType() {
        return type;
    }

    public Location type(String type) {

        this.type = type;
        return this;
    }
    @JsonIgnore
    public Double getLat(){
        return coordinates[1];
    }
    @JsonIgnore
    public Double getLng(){
        return coordinates[0];
    }

    @JsonIgnore
    public Long getRange() {
        return range;
    }
    @JsonIgnore
    public Double getRadius(){
        return (range/1000)/6378.1;

    }
    public static final Double getRadius(Long meters){
        return (meters/1000)/6378.1;

    }

    public Location range(Long range) {
        this.range = range;
        return this;
    }
}

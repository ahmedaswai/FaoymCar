package com.qcar.model.mongo.embedded;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ahmedissawi on 12/6/17.
 */
public class    Location {

    private Double[] coordinates = new Double[2];

    private String type = "Point";

    public Location() {

    }

    public Location(Double lng, Double lat) {

        this.coordinates = new Double[]{lng, lat};

    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @JsonIgnore
    public Double getLat(){
        return coordinates[1];
    }
    @JsonIgnore
    public Double getLng(){
        return coordinates[0];
    }

}

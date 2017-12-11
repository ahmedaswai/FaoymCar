package com.car.fayoum.model.mongo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */
@Entity("trips-data")
@Indexes(
        {
                @Index(value = "location", fields =@Field(value = "location",type = IndexType.GEO2DSPHERE))

        }
)
public class TripTrackingInfo{

    @Reference
    private Trip trip;
    private Location location;
    private Boolean valid;
    private Date updateTime;
    @Id
    private ObjectId id;


    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

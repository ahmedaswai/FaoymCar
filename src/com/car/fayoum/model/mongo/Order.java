package com.car.fayoum.model.mongo;

import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */

@Entity("orders")
@Indexes(
        {       @Index(value = "id", fields =@Field("id")),
                @Index(value = "location", fields =@Field(value = "location",type = IndexType.GEO2DSPHERE)),
                @Index(value = "destLocation", fields =@Field(value = "destLocation",type = IndexType.GEO2DSPHERE))

        }
)
public class Order  extends GenericEntity{
    private Location location;
    private Location destLocation;
    private Date requestTime;
    private Date tripProposedTime;

    private String notes;

    @Reference
    private  Customer customer;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getDestLocation() {
        return destLocation;
    }

    public void setDestLocation(Location destLocation) {
        this.destLocation = destLocation;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getTripProposedTime() {
        return tripProposedTime;
    }

    public void setTripProposedTime(Date tripProposedTime) {
        this.tripProposedTime = tripProposedTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getCollectionName() {
        return "orders";
    }
}

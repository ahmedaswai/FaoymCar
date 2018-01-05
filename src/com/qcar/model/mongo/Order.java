package com.qcar.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */


@Entity(value = "orders", noClassnameStored = true)
@Indexes(
        {@Index(value = "id", fields = @Field("id")),
                @Index(value = "location", fields = @Field(value = "location", type = IndexType.GEO2DSPHERE)),
                @Index(value = "destLocation", fields = @Field(value = "destLocation", type = IndexType.GEO2DSPHERE))

        }
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties
public class Order extends GenericEntity {
    private Location location;
    private Location destLocation;
    private Date requestTime;
    private Date tripProposedTime;

    private String notes;

    @Reference
    private Customer customer;

    public Location getLocation() {
        return location;
    }

    public void location(Location location) {
        this.location = location;
    }

    public Location getDestLocation() {
        return destLocation;
    }

    public void destLocation(Location destLocation) {
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
    @JsonIgnore
    public String getCollectionName() {
        return "orders";
    }
}

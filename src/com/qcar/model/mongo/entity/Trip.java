package com.qcar.model.mongo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
                @Index(value = "startLoc", fields = @Field(value = "startLoc", type = IndexType.GEO2DSPHERE))
        }
)
public class Trip extends GenericEntity {
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

    @Override
    @JsonIgnore
    public String getCollectionName() {
        return "trips";
    }
}

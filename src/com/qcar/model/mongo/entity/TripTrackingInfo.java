package com.qcar.model.mongo.entity;

import com.qcar.model.mongo.embedded.Location;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */

@Entity(value = "trips-data",noClassnameStored = true)
@Indexes(
        {
                @Index(value = "location", fields = @Field(value = "location", type = IndexType.GEO2DSPHERE))

        }
)

public class TripTrackingInfo {

    private Long tripId;
    private Location location;
    private Boolean valid;
    private Date updateTime;
    @Id
    private ObjectId id;

}

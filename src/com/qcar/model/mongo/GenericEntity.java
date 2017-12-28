package com.qcar.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */
public abstract class GenericEntity {
    @Id
    @JsonIgnore
    private ObjectId mongoId;

    protected Long id;

    private Integer updatedBy;
    private String clientInfo;
    private Date updatedOn = new Date();

    public ObjectId getMongoId() {
        return mongoId;
    }

    public GenericEntity mongoId(ObjectId mongoId) {

        this.mongoId = mongoId;
        return this;

    }

    public GenericEntity id(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }


    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public GenericEntity updatedBy(Integer updatedBy) {

        this.updatedBy = updatedBy;
        return this;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public GenericEntity setClientInfo(String clientInfo) {

        this.clientInfo = clientInfo;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public GenericEntity setUpdatedOn(Date updatedOn) {

        this.updatedOn = updatedOn;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append("mongoId=").append(mongoId);
        sb.append(", id=").append(id);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", clientInfo='").append(clientInfo).append('\'');
        sb.append(", updatedOn=").append(updatedOn);

        return sb.toString();
    }

    public abstract String getCollectionName();

}

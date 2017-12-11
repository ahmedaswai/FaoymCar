package com.car.fayoum.model.mongo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */
public abstract class GenericEntity {
    @Id
    private ObjectId mongoId;
    private Long id;

    private Integer updatedBy;
    private String clientInfo;
    private Date updatedOn=new Date();

    public ObjectId getMongoId() {
        return mongoId;
    }

    public void setMongoId(ObjectId mongoId) {
        this.mongoId = mongoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public abstract String getCollectionName();
    @PrePersist
    public void prePresist(){
        if(id==null){

        }
    }
}

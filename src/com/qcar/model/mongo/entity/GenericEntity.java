package com.qcar.model.mongo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qcar.model.service.ClientInfo;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */
public abstract class GenericEntity {



    @Id
    protected Long id;

    private Long updatedBy;
    private ClientInfo clientInfo;
    private Date updatedOn = new Date();



    public GenericEntity id(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }


    public Long getUpdatedBy() {
        return updatedBy;
    }

    public GenericEntity updatedBy(Long updatedBy) {

        this.updatedBy = updatedBy;
        return this;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public GenericEntity clientInfo(ClientInfo clientInfo) {

        this.clientInfo = clientInfo;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public GenericEntity updatedOn(Date updatedOn) {

        this.updatedOn = updatedOn;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");

        sb.append(", id=").append(id);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", clientInfo='").append(clientInfo).append('\'');
        sb.append(", updatedOn=").append(updatedOn);

        return sb.toString();
    }

    @JsonIgnore
    public Boolean isCached(){
        return true;
    }
    public abstract String getCollectionName();
    public  GenericEntity updatedCachedEntity(){
        return this;
    }


}

package com.qcar.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;

/**
 * Created by ahmedissawi on 12/6/17.
 */
@Entity(value = "customers", noClassnameStored = true)
@Indexes(
        {@Index(value = "id", fields = @Field("id"))
                , @Index(value = "updatedOn", fields = @Field(value = "updatedOn", type = IndexType.DESC))
                , @Index(value = "currentLocation", fields = @Field(value = "currentLocation", type = IndexType.GEO2DSPHERE))
        }
)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Customer extends GenericEntity {

    private String fullName;
    private Integer city;
    private String homeAddress;
    private Location homeLocation;

    private Location currentLocation;

    private String mobileNumber1;
    private String mobileNumber2;
    private String homePhoneNumber;
    private String notes;
    private Boolean status = true;
    private Boolean inTrip = false;
    private Double rating;
    private Double credit;

    public static final Customer instance() {
        return new Customer();
    }

    public String getFullName() {
        return fullName;
    }

    public Customer fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getCity() {
        return city;
    }

    public Customer city(Integer city) {
        this.city = city;
        return this;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public Customer homeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public Location getHomeLocation() {
        return homeLocation;
    }

    public Customer homeLocation(Location homeLocation) {
        this.homeLocation = homeLocation;
        return this;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Customer currentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        return this;
    }

    public String getMobileNumber1() {
        return mobileNumber1;
    }

    public Customer mobileNumber1(String mobileNumber1) {
        this.mobileNumber1 = mobileNumber1;
        return this;
    }

    public String getMobileNumber2() {
        return mobileNumber2;
    }

    public Customer mobileNumber2(String mobileNumber2) {
        this.mobileNumber2 = mobileNumber2;
        return this;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public Customer homePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Customer notes(String notes) {
        this.notes = notes;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public Customer status(Boolean status) {
        this.status = status;
        return this;
    }

    public Boolean getInTrip() {
        return inTrip;
    }

    public Customer inTrip(Boolean inTrip) {
        this.inTrip = inTrip;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public Customer rating(Double rating) {
        this.rating = rating;
        return this;
    }

    public Double getCredit() {
        return credit;
    }

    public Customer credit(Double credit) {
        this.credit = credit;
        return this;
    }

    @Override
    @JsonIgnore
    public String getCollectionName() {
        return "customers";
    }
}

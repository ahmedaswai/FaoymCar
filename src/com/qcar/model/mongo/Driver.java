package com.qcar.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */
@Entity("drivers")
@Indexes(
        {@Index(value = "id", fields = @Field("id"))
                , @Index(value = "fullName", fields = @Field(value = "fullName", type = IndexType.TEXT))
                , @Index(value = "currentLocation", fields = @Field(value = "currentLocation", type = IndexType.GEO2DSPHERE))
        }
)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Driver extends GenericEntity {

    public  static final Driver instance(){
        return new Driver();
    }
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
    private Boolean online = false;
    private Double rating=0d;

    private String idNumber;
    private String licenseNumber;

    private String carLicenseNumber;
    private String carPlateNumber;
    private Date carLicenseExpiryDate;
    private Integer carLicenseCity;

    private Date birthDate;

    private Double credit=0d;

    public String getFullName() {
        return fullName;
    }

    public Driver fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getCity() {
        return city;
    }

    public Driver city(Integer city) {
        this.city = city;
        return this;
    }

    public String homeAddress() {
        return homeAddress;
    }

    public Driver homeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public Location getHomeLocation() {
        return homeLocation;
    }

    public Driver homeLocation(Location homeLocation) {
        this.homeLocation = homeLocation;
        return this;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Driver currentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        return this;
    }

    public String getMobileNumber1() {
        return mobileNumber1;
    }

    public Driver mobileNumber1(String mobileNumber1) {
        this.mobileNumber1 = mobileNumber1;
        return this;
    }

    public String getMobileNumber2() {
        return mobileNumber2;
    }

    public Driver mobileNumber2(String mobileNumber2) {
        this.mobileNumber2 = mobileNumber2;
        return this;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public Driver homePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Driver setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public Driver setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public Boolean getOnline() {
        return online;
    }

    public Driver online(Boolean online) {
        this.online = online;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public Driver rating(Double rating) {
        this.rating = rating;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Driver idNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Driver licenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        return this;
    }

    public String getCarLicenseNumber() {
        return carLicenseNumber;
    }

    public Driver carLicenseNumber(String carLicenseNumber) {
        this.carLicenseNumber = carLicenseNumber;
        return this;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public Driver carPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
        return this;
    }

    public Date getCarLicenseExpiryDate() {
        return carLicenseExpiryDate;
    }

    public Driver carLicenseExpiryDate(Date carLicenseExpiryDate) {
        this.carLicenseExpiryDate = carLicenseExpiryDate;
        return this;
    }

    public Integer getCarLicenseCity() {
        return carLicenseCity;
    }

    public Driver carLicenseCity(Integer carLicenseCity) {
        this.carLicenseCity = carLicenseCity;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Driver birthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Double getCredit() {
        return credit;
    }

    public Driver credit(Double credit) {
        this.credit = credit;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Driver{");
        sb.append("fullName='").append(fullName).append('\'');
        sb.append(", city=").append(city);
        sb.append(", homeAddress='").append(homeAddress).append('\'');
        sb.append(", homeLocation=").append(homeLocation);
        sb.append(", currentLocation=").append(currentLocation);
        sb.append(", mobileNumber1='").append(mobileNumber1).append('\'');
        sb.append(", mobileNumber2='").append(mobileNumber2).append('\'');
        sb.append(", homePhoneNumber='").append(homePhoneNumber).append('\'');
        sb.append(", notes='").append(notes).append('\'');
        sb.append(", status=").append(status);
        sb.append(", online=").append(online);
        sb.append(", rating=").append(rating);
        sb.append(", idNumber='").append(idNumber).append('\'');
        sb.append(", licenseNumber='").append(licenseNumber).append('\'');
        sb.append(", carLicenseNumber='").append(carLicenseNumber).append('\'');
        sb.append(", carPlateNumber='").append(carPlateNumber).append('\'');
        sb.append(", carLicenseExpiryDate=").append(carLicenseExpiryDate);
        sb.append(", carLicenseCity=").append(carLicenseCity);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", credit=").append(credit);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    @JsonIgnore
    public String getCollectionName() {
        return "drivers";
    }
}

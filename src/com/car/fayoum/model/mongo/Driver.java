package com.car.fayoum.model.mongo;

import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.utils.IndexType;

import java.util.Date;

/**
 * Created by ahmedissawi on 12/6/17.
 */
@Entity("drivers")
@Indexes(
        {@Index(value = "id", fields =@Field("id")),
        @Index(value = "homeLocation", fields =@Field(value = "homeLocation",type = IndexType.GEO2DSPHERE))
        }
)
public class Driver extends GenericEntity{
    private String fullName;
    private Integer city;


    private String homeAddress;
    private Location homeLocation;

    private Location currentLocation;

    private String mobileNumber1;
    private String mobileNumber2;
    private String homePhoneNumber;
    private String notes;
    private Boolean status=true;
    private Boolean online=false;
    private Double rating;

    private Long idNumber;
    private Long licenseNumber;

    private Long carLicenseNumber;
    private String carPlateNumber;
    private Date carLicenseExpiryDate;
    private Integer carLicenseCity;

    private Date birthDate;

    private Double credit;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }



    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getMobileNumber1() {
        return mobileNumber1;
    }

    public void setMobileNumber1(String mobileNumber1) {
        this.mobileNumber1 = mobileNumber1;
    }

    public String getMobileNumber2() {
        return mobileNumber2;
    }

    public void setMobileNumber2(String mobileNumber2) {
        this.mobileNumber2 = mobileNumber2;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Long getCarLicenseNumber() {
        return carLicenseNumber;
    }

    public void setCarLicenseNumber(Long carLicenseNumber) {
        this.carLicenseNumber = carLicenseNumber;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public Date getCarLicenseExpiryDate() {
        return carLicenseExpiryDate;
    }

    public void setCarLicenseExpiryDate(Date carlicenseExpiryDate) {
        this.carLicenseExpiryDate = carlicenseExpiryDate;
    }

    public Integer getCarLicenseCity() {
        return carLicenseCity;
    }

    public void setCarLicenseCity(Integer carLicenseCity) {
        this.carLicenseCity = carLicenseCity;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Location getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(Location homeLocation) {
        this.homeLocation = homeLocation;
    }
    @Override
    public String getCollectionName() {
        return "drivers";
    }
}

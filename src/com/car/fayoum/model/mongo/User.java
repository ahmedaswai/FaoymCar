package com.car.fayoum.model.mongo;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;


/**
 * Created by ahmedissawi on 12/6/17.
 */
@Entity("users")
@Indexes(
        @Index(value = "id", fields =@Field("id"))
)
public class User extends  GenericEntity{


    private String userName;
    private String password;
    private Boolean status;
    private Integer permissions;
    private Integer userType;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCollectionName(){
        return "users";
    }


}

package com.qcar.model.mongo;


import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ahmedissawi on 12/6/17.
 */
@Entity("users")
@Indexes(
        {
                @Index(value = "id", fields = @Field("id"), options = @IndexOptions(unique = true)),
                @Index(value = "loginName", fields = @Field("loginName"), options = @IndexOptions(unique = true))
        }
)
public class User extends GenericEntity {


    private String userName;
    private String password;
    private Boolean status;

    private Integer userType;
    private String loginName;
    private Boolean currentlyLogined;

    private List<Permission> permissionList = new ArrayList<>();

    public User id(Long id) {
        this.id = id;
        return this;
    }

    public static User instance() {
        return new User();
    }

    public String getUserName() {
        return userName;
    }


    public User userName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getLoginName() {
        return loginName;
    }

    public User loginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public User status(Boolean status) {
        this.status = status;
        return this;
    }

    public User currentlyLogined(Boolean currentlyLogined) {
        this.currentlyLogined = currentlyLogined;

        return this;
    }

    public Boolean getCurrentlyLogined() {
        return this.currentlyLogined;
    }

    public Integer getUserType() {
        return userType;
    }

    public User userType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", status=").append(status);

        sb.append(", userType=").append(userType);
        sb.append(", loginName='").append(loginName).append('\'');
        sb.append(", permissions='").append(permissionList.toString()).append('\'');

        sb.append(",").append(super.toString()).append('\'');

        sb.append('}');
        return sb.toString();
    }

    public String getCollectionName() {
        return "users";
    }


}

package com.qcar.model.mongo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ahmedissawi on 12/6/17.
 */
@Entity("users")
@Indexes(
        {
                @Index(value = "id", fields = @Field("id")),
                @Index(value = "loginName", fields = @Field("loginName"), options = @IndexOptions(unique = true))
        }
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends GenericEntity {


    private String userName;
    private String password;
    private Boolean status=true;

    private UserType userType;
    private String loginName;
    private Boolean currentlyLogined=false;

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

    public UserType getUserType() {
        return userType;
    }

    public User userType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public User permissionList(List<Permission>p) {
        this.permissionList=p;
        return this;
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

    @JsonIgnore
    public String getCollectionName() {
        return "users";
    }


}

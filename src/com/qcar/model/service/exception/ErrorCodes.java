package com.qcar.model.service.exception;


import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCodes {

    USER_NOT_FOUND(1000,"User Not Found"),
    INVALID_PASSWORD(1001,"Invalid Password"),
    USER_NOT_ACTIVE(1002,"User is Not Active"),
    PASSWORD_RESET_IS_SAME(1003,"Can not reset password with the same password"),
    INVALID_TOKEN(1004,"Invalid Token"),
    ERROR_UPLOAD(3000,"Error During Upload"),
    ERROR_DOWNLOAD(3001,"Error During Download"),
    MONGO_SERVER_IS_DOWN(4000,"Mongo Server is Down"),
    UN_DEFINED_EXCEPTION(9001,"Undefined Exception"),
    SERVER_ROUTE_ERROR(9002,"Service Route is Invalid");







    private final int errorCode;
    private final String errorMessage;
    private ErrorCodes(int errorCode,String errorMessage){
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }
    @JsonValue
    public int getErrorCode(){
        return this.errorCode;

    }
    public  String getErrorMessage(){
        return this.errorMessage;
    }

}

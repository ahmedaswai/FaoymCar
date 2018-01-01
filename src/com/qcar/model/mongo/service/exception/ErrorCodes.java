package com.qcar.model.mongo.service.exception;


import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCodes {

    USER_NOT_FOUND(1000,"User Not Found"),
    INVALID_PASSWORD(1001,"Invalid Password"),
    PASSWORD_RESET_IS_SAME(1002,"Can not reset password with the same password");

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
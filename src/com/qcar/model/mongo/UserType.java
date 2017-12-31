package com.qcar.model.mongo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
    Admin(0),Owner(1),Driver(2),Customer(3);
    final int value;

    private UserType(int value){
        this.value=value;
    }
    @JsonValue
    public int getValue(){
        return value;
    }
}

package com.qcar.model.mongo.choicelist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UserType {
    NONE(-1),Admin(0),Owner(1),Driver(2),Customer(3);
    final int value;

    private UserType(int value){
        this.value=value;
    }
    @JsonValue
    public int getValue(){
        return value;
    }

    private static final Map<Integer,UserType> mp;

    static {
        UserType[] values = UserType.values();
        mp = Arrays.stream(values).collect(Collectors.toMap(UserType::getValue, Function.identity()));
    }

    public static UserType getUserType(Integer value){
        if(value==null){
            return NONE;
        }
        return mp.get(value);
    }
}

package com.qcar.model.mongo.choicelist;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum TripStatus {
    NONE(-1),SCHEDULED(0),ACTIVE(1),CANCELED(2),FINISHED(3);
    final int value;
    private static final Map<Integer,TripStatus> mp;

    static {
        TripStatus[] tripStatuses = TripStatus.values();
        mp = Arrays.stream(tripStatuses).collect(Collectors.toMap(TripStatus::getValue, Function.identity()));
    }
    private TripStatus(int value){
        this.value=value;
    }

    public static TripStatus getTripStatus(Integer value){
        if(value==null){
            return NONE;
        }
       return mp.get(value);
    }
    @JsonValue
    public int getValue(){
        return value;
    }
}

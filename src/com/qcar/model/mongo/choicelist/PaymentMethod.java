package com.qcar.model.mongo.choicelist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethod {
    Cash(0),Credit_Card(1),Customer_Credit(2);
    final int value;

    private PaymentMethod(int value){
        this.value=value;
    }
    @JsonValue
    public int getValue(){
        return value;
    }
}

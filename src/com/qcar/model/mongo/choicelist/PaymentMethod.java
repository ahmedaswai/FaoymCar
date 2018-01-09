package com.qcar.model.mongo.choicelist;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PaymentMethod {
    NONE(-1),Cash(0),Credit_Card(1),Customer_Credit(2);
    final int value;
    private static final Map<Integer,PaymentMethod> mp;

    static {
        PaymentMethod[] paymentMethods = PaymentMethod.values();
        mp = Arrays.stream(paymentMethods).collect(Collectors.toMap(PaymentMethod::getValue, Function.identity()));
    }
    private PaymentMethod(int value){
        this.value=value;
    }

    public static PaymentMethod getPayment(Integer value){
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

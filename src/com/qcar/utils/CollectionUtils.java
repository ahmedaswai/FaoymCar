package com.qcar.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.qcar.model.mongo.choicelist.PaymentMethod;
import com.qcar.model.mongo.choicelist.TripStatus;
import com.qcar.model.mongo.choicelist.UserType;
import com.qcar.model.mongo.embedded.Location;
import io.vertx.core.MultiMap;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by ahmedissawi on 12/23/17.
 */
public final class CollectionUtils {
    public static <T> Boolean isEmpty(Collection<T> col) {
        return col == null || col.isEmpty();
    }
    public static final TypeReference<List<Long>>LONG_LIST_TYPE=new TypeReference<List<Long>>() {
        @Override
        public Type getType() {
            return super.getType();
        }
    };
    public   static final <T>  T getParamValue(MultiMap mp,String k,Class <T>t){

        Object value=null;

         if(mp.contains(k)||mp.contains(k+"Range")){
            String v= mp.get(k);
            switch (t.getSimpleName()){
                case "Long":
                    value=Long.parseLong(v);
                    break;
                case "Integer":
                    value=Integer.parseInt(v);
                    break;
                case "Double":
                    value=Double.parseDouble(v);
                    break;

                case "Boolean":
                    value=Boolean.parseBoolean(v);
                    break;
                case "String":
                    value=String.valueOf(v);
                    break;
                case "PaymentMethod":
                    value= PaymentMethod.getPayment(Integer.parseInt(v));
                    break;
                case "TripStatus":
                    value= TripStatus.getTripStatus(Integer.parseInt(v));
                    break;

                case "UserType":
                    value= UserType.getUserType(Integer.parseInt(v));
                    break;
                case "Date":
                    value= new Date(Long.parseLong(v)*1000);
                    break;
                case "Location":
                    value=castLocation(mp,k);
                    break;

            }


        }
        return t.cast(value);
    }
    private static final Location castLocation(MultiMap mp,String k){
        if(mp.contains(k+"Lat") &&mp.contains(k+"Lng")&&mp.contains(k+"Range"))
            return Location.instance(mp,k);
        return null;
    }
}

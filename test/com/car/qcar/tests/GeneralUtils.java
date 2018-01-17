package com.car.qcar.tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcar.model.mongo.entity.Customer;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.mongo.entity.Trip;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class GeneralUtils {

    @Test
    public void testTime(){

        Long d=1515429814L*1000;

        Timestamp stamp = new Timestamp(d);
        Date date = new Date(stamp.getTime());
        System.out.println(SimpleDateFormat.getInstance().format(date));

    }
    @Test
    @Ignore
    public void testGetExtension(){

        assertEquals (com.qcar.utils.GeneralUtils.getExtension("fi.le.png"),"png");
    }

    @Test
    @Ignore
    public void testJsonCustomer() throws JsonProcessingException {
        Customer customer = Customer.instance();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(customer));
        assertEquals(1, 1);
    }
    @Test
    @Ignore
    public void testJsonOrder() throws JsonProcessingException {
        Order order = Order.instance();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        System.out.println(mapper.writeValueAsString(order));
        assertEquals(1, 1);
    }
    @Test
    public void testJsonTrip() throws JsonProcessingException {
        Trip order = Trip.instance();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        System.out.println(mapper.writeValueAsString(order));
        assertEquals(1, 1);
    }
}

package com.car.fayoum.dao.tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qcar.model.mongo.Customer;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeneralUtils {
    @Test
    public void testGetExtension(){

        assertEquals (com.qcar.utils.GeneralUtils.getExtension("fi.le.png"),"png");
    }

    @Test
    public void testJsonCustomer() throws JsonProcessingException {
        Customer customer = Customer.instance();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(customer));
        assertEquals(1, 1);
    }
}

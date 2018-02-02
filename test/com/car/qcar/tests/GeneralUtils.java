package com.car.qcar.tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcar.model.mongo.choicelist.PaymentMethod;
import com.qcar.model.mongo.choicelist.Permission;
import com.qcar.model.mongo.choicelist.UserType;
import com.qcar.model.mongo.embedded.Location;
import com.qcar.model.mongo.entity.*;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    public void testGetExtension() throws JsonProcessingException {

        User u=User.instance().
                loginName("bmind").password("123").userName("Ahmed Khamis").
                status(true).id(12L).
                online(false).permissionList(Arrays.asList(Permission.ADD_CUSTOMER,
                Permission.ADD_TRIP,Permission.REMOVE_CUSTOMER)).userType(UserType.Admin);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(u));



    }

    @Test
    public void testOrder() throws JsonProcessingException {
        Customer customer = Customer.
                instance().city(12).credit(40.5).
                currentLocation(new Location().

                        coordinates(new Double[]{44.33,44.33})).
                fullName("حامد").
                homeAddress("المسله").
                homePhoneNumber("+444").inTrip(false).
                mobileNumber1("+444").
                mobileNumber2("+444").
                notes("محمود السيد").
                status(true);

        Order u=Order.instance().orderNum(201500001L).customer(customer).fromLocation(new Location().

                coordinates(new Double[]{44.33,44.33})).toLocation(new Location().

                coordinates(new Double[]{44.33,44.33})).orderLocation(new Location().

                coordinates(new Double[]{44.33,44.33})).
                orderTime(new Date()).
                paymentMethod(PaymentMethod.Customer_Credit).notes("ss").
                status(true).tripStartTime(new Date()).tripEndTime(new Date());
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(u));
    }




    @Test
    public void testTripJson() throws JsonProcessingException {

        Customer customer = Customer.
                instance().city(12).credit(40.5).
                currentLocation(new Location().

                        coordinates(new Double[]{44.33,44.33})).
                fullName("حامد").
                homeAddress("المسله").
                homePhoneNumber("+444").inTrip(false).
                mobileNumber1("+444").
                mobileNumber2("+444").
                notes("محمود السيد").
                status(true);
        Driver d=Driver.instance().setStatus(true).birthDate(new Date()).
                carLicenseCity(12).carLicenseNumber("3333").
                carLicenseExpiryDate(new Date()).credit(222d).homeAddress("fdfsdf").
                homeLocation(new Location().coordinates(new Double[]{44.3,33.3})).idNumber("333").
                licenseNumber("3333").mobileNumber1("33333").
                mobileNumber2("ddddd").
                homePhoneNumber("ddddd").
                online(true).rating(2.5).
                notes("sdsadasd").
                fullName("آحمد علي");

        Order order=Order.instance().orderNum(201500001L).customer(customer).fromLocation(new Location().

                coordinates(new Double[]{44.33,44.33})).toLocation(new Location().

                coordinates(new Double[]{44.33,44.33})).orderLocation(new Location().

                coordinates(new Double[]{44.33,44.33})).
                orderTime(new Date()).
                paymentMethod(PaymentMethod.Customer_Credit).notes("ss").
                status(true).tripStartTime(new Date()).tripEndTime(new Date());

        ObjectMapper mapper = new ObjectMapper();
        Trip trip=Trip.instance().tripNumber(200900004L)
                .actualCost(45d).actualDistance(33d).
                        actualEndTime(new Date()).actualStartTime(new Date()).driver(d).order(order).
                        endLoc(new Location().coordinates(new Double[]{44.3,33.3})).
                        startLoc(new Location().coordinates(new Double[]{44.3,33.3})).proposedCost(40d).
                        notes("ssss").proposedDistance(33d).proposedEndTime(new Date());

        trip.id(333L);
        System.out.println(mapper.writeValueAsString(trip));



    }

    @Test
    public void testGetDriver() throws JsonProcessingException {

        Driver u=Driver.instance().setStatus(true).birthDate(new Date()).
                carLicenseCity(12).carLicenseNumber("3333").
                carLicenseExpiryDate(new Date()).credit(222d).homeAddress("fdfsdf").
                homeLocation(new Location().coordinates(new Double[]{44.3,33.3})).idNumber("333").licenseNumber("3333").mobileNumber1("33333").
                mobileNumber2("ddddd").homePhoneNumber("ddddd").online(true).
                rating(2.5). notes("sdsadasd").
                fullName("آحمد علي");;
        u.id(33L);


        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(u));



    }

    @Test
    public void testJsonCustomer() throws JsonProcessingException {
        Customer customer = Customer.
                instance().city(12).credit(40.5).
                currentLocation(new Location().

                        coordinates(new Double[]{44.33,44.33})).
                fullName("حامد").
                homeAddress("المسله").
                homePhoneNumber("+444").inTrip(false).
                mobileNumber1("+444").
                mobileNumber2("+444").
                notes("محمود السيد").
                status(true);
        customer.id(3L);

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
    @Ignore
    public void testJsonTrip() throws JsonProcessingException {
        Trip order = Trip.instance();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        System.out.println(mapper.writeValueAsString(order));
        assertEquals(1, 1);
    }

}

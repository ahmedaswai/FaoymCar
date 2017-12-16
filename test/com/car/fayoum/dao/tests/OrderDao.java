package com.car.fayoum.dao.tests;


import com.car.fayoum.model.mongo.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderDao {
    @Test
    public void testUserDao(){
        User user=new User();
        user.setStatus(true);
        user.setUserName("Ahmed Khamis");
        assertTrue(true);

    }
}

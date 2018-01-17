package com.car.qcar.tests;

import com.qcar.dao.DaoFactory;
import com.qcar.model.mongo.entity.User;
import com.qcar.security.SecurityUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;


/**
 * Created by ahmedissawi on 12/23/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDao {


    private com.qcar.dao.UserDao dao;
    @Before
    public void init(){
        dao = DaoFactory.userDao();
    }

    @Test
    public void test1() throws Exception {

        User u= User.instance().
                password(SecurityUtils.hashPassword("sss")).userName("احمد خميس").
                status(true).loginName("bmind");

        dao.save(u);
        assertNotNull(u.getId());
    }

    @Test
    public void test2() throws Exception {
        assertTrue(SecurityUtils.checkPassword("sss", "$2a$10$ld4kULzM2t3Msulu1kOhS.NTeUpyA.MeiHXmg8mnqV3rR1E5Yxegy"));
    }
    @Test
    public void test3() throws Exception {


        assertTrue(dao.isExistsByLoginName(User.instance().loginName("bmind")));
    }
    @Test
    public void test4() throws Exception {

        String user = dao.findUserByLoginName("bmind").toString();

        assertNotNull(user);
    }

    @Test
    public void test5() throws Exception {

        User user = dao.findUserByLoginName("bmind").get();

        dao.saveOrMerge(user);

        assertNotNull(user);
    }

    @Test
    public void test6() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        User u = User.instance().
                password(SecurityUtils.hashPassword("sss")).
                userName("احمد خميس").
                status(true).loginName("bmind");
        String json = mapper.writeValueAsString(u);

        System.out.println(json);


    }
}

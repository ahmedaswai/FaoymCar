package com.car.fayoum.dao.tests;

import com.car.fayoum.model.mongo.User;
import com.car.fayoum.utils.SecurityUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by ahmedissawi on 12/23/17.
 */
public class UserDao {

    private com.car.fayoum.dao.UserDao dao;
    @Before
    public void init(){
         dao=new com.car.fayoum.dao.UserDao();
    }
    @Test
    public void testSaving() throws Exception {

        User u= User.instance().
                password(SecurityUtils.hashPassword("sss")).
                permissions(33).userName("احمد خميس").
                status(true).loginName("bmind");

        dao.save(u);
        assertNotNull(u.getId());
    }
    @Test
    public void testCheckingPassword() throws Exception {
        assertTrue(SecurityUtils.checkPassword("sssss","$2a$10$ld4kULzM2t3Msulu1kOhS.NTeUpyA.MeiHXmg8mnqV3rR1E5Yxegy"));
    }
    @Test
    public void testUserExists() throws Exception {

        assertTrue(dao.isExists(User.instance().id(15L)));
        assertTrue(dao.isExistsByLoginName(User.instance().loginName("bmind")));
    }
    @Test
    public void testFindUser() throws Exception {

        String user= dao.findUserByLoginName(User.instance().loginName("bmind")).toString() ;
        System.out.println(user);
        assertNotNull(user);
    }
}

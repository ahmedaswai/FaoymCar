package com.car.fayoum.dao;

import com.car.fayoum.model.mongo.GenericEntity;
import com.car.fayoum.model.mongo.User;
import com.car.fayoum.utils.CollectionUtils;
import com.mongodb.CursorType;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * Created by ahmedissawi on 12/23/17.
 */
public class UserDao extends GenericDao<User> implements IDao<User>{

    public static final String LOGN_NAME="loginName";
    @Override
    public GenericEntity update(User user) {
        return null;
    }

    @Override
    public List<GenericEntity> findByExample(User user) {
        return null;
    }

    @Override
    public Boolean delete(User user) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }


    public Boolean isExistsByLoginName(User user) {

        Query query=getDataStore().
                createQuery(User.class).field(LOGN_NAME).
                equal(user.getLoginName());

        long count=query.count();
        return count>0;
    }
    public User findUserByLoginName(User user){
        Query<User> query=getDataStore().
                createQuery(User.class).field(LOGN_NAME).
                equal(user.getLoginName());
        FindOptions options=new FindOptions();

        options.limit(1);

        List<User>userList= query.asList(options);
        if(CollectionUtils.isEmpty(userList)){
            throw new RuntimeException("User is not Existing");
        }
        User u=userList.get(0);
        return u;
    }
}

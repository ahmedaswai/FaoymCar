package com.qcar.dao;

import com.qcar.model.mongo.User;
import com.qcar.utils.CollectionUtils;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;

import java.util.List;
import java.util.Optional;


/**
 * Created by ahmedissawi on 12/23/17.
 */

public class UserDao extends GenericDao<User> implements IDao<User> {

    public static final String LOGIN_NAME = "loginName";


    UserDao() {

    }

    @Override
    public List<User> findByExample(User user) {
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

        Query query = getDataStore().
                createQuery(User.class).field(LOGIN_NAME).
                equal(user.getLoginName());

        long count = query.count();
        return count > 0;
    }

    public Optional<User> findUserByLoginName(String user) {

        User u = null;
        Query<User> query = getDataStore().
                createQuery(User.class).field(LOGIN_NAME).
                equal(user);

        FindOptions options = new FindOptions();


        options.limit(1);

        List<User> userList = query.asList(options);

        if (!CollectionUtils.isEmpty(userList)) {
            u = userList.get(0);

        }
        return Optional.ofNullable(u);
    }

    public User findUserById(Long id) {
        Query<User> query = getDataStore().
                createQuery(User.class).field(ID).
                equal(id);
        FindOptions options = new FindOptions();


        options.limit(1);

        List<User> userList = query.asList(options);
        if (CollectionUtils.isEmpty(userList)) {
            throw new RuntimeException("User is not Existing");
        }
        User u = userList.get(0);
        return u;
    }
}

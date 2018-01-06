package com.qcar.dao;

import com.qcar.model.mongo.entity.User;
import com.qcar.service.cache.QCarCache;
import com.qcar.utils.CollectionUtils;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;

import java.util.List;
import java.util.Optional;


/**
 * Created by ahmedissawi on 12/23/17.
 */

public class UserDao extends GenericDao<User> implements IDao<User>,IStatusDao {

    public static final String LOGIN_NAME = "loginName";


    UserDao(QCarCache cache) {

        super(cache);
    }


    @Override
    public List<User> findByExample(User user) {
        return null;
    }



    public User changeStatus(Long id,Boolean status){

        return (User) changeStatus(this,id,status,User.class);
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
    public Boolean isExistsByLoginName(User user) {

        Query query = getDataStore().
                createQuery(User.class).field(LOGIN_NAME).
                equal(user.getLoginName());

        long count = query.count();
        return count > 0;
    }

    public List<User> findAllActive() {
        return findAllActive(this, User.class);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User getEntity() {
        return User.instance();
    }
}

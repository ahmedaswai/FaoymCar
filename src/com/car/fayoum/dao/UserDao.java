package com.car.fayoum.dao;


import com.car.fayoum.model.mongo.*;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.converters.StringConverter;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ahmedissawi on 12/6/17.
 */
public class UserDao extends GenericDao<User> implements IDao<User> {



    @Override
    public Boolean add(User user) {
        setId(user);
        return getDataStore().save(user)!=null;
    }

    @Override
    public Boolean update(User user) {
        return null;
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

}

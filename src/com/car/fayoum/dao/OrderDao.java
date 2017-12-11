package com.car.fayoum.dao;

import com.car.fayoum.model.mongo.Order;

import java.util.List;

/**
 * Created by ahmedissawi on 12/7/17.
 */
public class OrderDao implements IDao<Order> {
    @Override
    public Boolean add(Order order) {
       return getDataStore().save(order)!=null;
    }

    @Override
    public Boolean update(Order order) {
        return null;
    }

    @Override
    public List<Order> findByExample(Order order) {
        return null;
    }

    @Override
    public Boolean delete(Order order) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }
}

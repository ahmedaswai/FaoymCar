package com.car.fayoum.dao;

import com.car.fayoum.model.mongo.GenericEntity;
import com.car.fayoum.model.mongo.Order;

import java.util.List;

/**
 * Created by ahmedissawi on 12/7/17.
 */
public class OrderDao extends GenericDao<Order> implements IDao<Order> {

    @Override
    public Order update(Order order) {
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

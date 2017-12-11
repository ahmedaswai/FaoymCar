package com.car.fayoum.dao;

import com.car.fayoum.model.mongo.Customer;

import java.util.List;

/**
 * Created by ahmedissawi on 12/7/17.
 */
public class CustomerDao  implements IDao<Customer>{
    @Override
    public Boolean add(Customer customer) {
       return getDataStore().save(customer)!=null;
    }

    @Override
    public Boolean update(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> findByExample(Customer customer) {
        return null;
    }

    @Override
    public Boolean delete(Customer customer) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }
}

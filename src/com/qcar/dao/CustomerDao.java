package com.qcar.dao;

import com.qcar.model.mongo.Customer;
import com.qcar.service.cache.QCarCache;

import java.util.List;

public class CustomerDao extends GenericDao<Customer> implements IDao<Customer>, IStatusDao {


    public CustomerDao(QCarCache cache) {
        super(cache);
    }

    public static final String CURRENT_LOCATION = "currentLocation";
    public static final String STATUS = "status";
    public static final String ONLINE = "online";

    @Override
    public List<Customer> findByExample(Customer driver) {
        return null;
    }


    public Customer changeStatus(Long id, Boolean status) {

        return (Customer) changeStatus(this, id, status, Customer.class);
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    public List<Customer> findAllActive() {
        return findAllActive(this, Customer.class);
    }

    @Override
    public Customer getEntity() {
        return Customer.instance();
    }
}
package com.car.fayoum.dao;

import org.mongodb.morphia.Datastore;

import java.util.List;

/**
 * Created by ahmedissawi on 12/7/17.
 */
public interface IDao<T>{
     Boolean add(T t);
     Boolean update(T t);
     List<T>findByExample(T t);
     Boolean delete(T t);
     Boolean deleteById(Long id);
     default Datastore getDataStore(){
         return DatabaseClient.INSTANCE.
                 startScanning("com.car.fayoum.model.mongo").
                 connect().db("fayoum-car").datastore();
     }



}

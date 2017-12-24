package com.car.fayoum.dao;

import com.car.fayoum.model.mongo.GenericEntity;
import org.mongodb.morphia.Datastore;

import java.util.List;

/**
 * Created by ahmedissawi on 12/7/17.
 */
public interface IDao<T>{
     GenericEntity save(GenericEntity t);
     GenericEntity update(T t);
     List<GenericEntity>findByExample(T t);
     Boolean delete(T t);
     Boolean deleteById(Long id);

     default Datastore getDataStore(){
         return DatabaseClient.INSTANCE.
                 startScanning("com.car.fayoum.model.mongo").
                 connect().db("fayoum-car").datastore();
     }



}

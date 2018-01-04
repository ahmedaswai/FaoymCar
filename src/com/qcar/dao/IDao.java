package com.qcar.dao;

import com.qcar.model.mongo.GenericEntity;
import org.mongodb.morphia.Datastore;

import java.util.List;

/**
 * Created by ahmedissawi on 12/7/17.
 */
public interface IDao<T extends GenericEntity> {
    T save(T t);

    T update(T t);

    T saveOrMerge(T t);

    List<T> findByExample(T t);

    Boolean delete(T t);

    Boolean deleteById(Long id);

    default T findByIdAndSave(Long id,T t){
        return null;
    }


}

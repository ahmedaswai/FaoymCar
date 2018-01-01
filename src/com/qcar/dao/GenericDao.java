package com.qcar.dao;

import com.qcar.model.mongo.GenericEntity;
import com.qcar.model.mongo.Sequence;
import com.mongodb.WriteConcern;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

/**
 * Created by ahmedissawi on 12/8/17.
 */
public abstract class GenericDao<T extends GenericEntity> implements IDao<T> {


    public static final String ID = "id";

    public Datastore getDataStore() {

       return DatabaseClient.INSTANCE.connect().db("qa-car").startScanning("com.qcar.model.mongo").
                datastore();

    }

    @Override
    public T update(T t) {
        WriteConcern concern = WriteConcern.ACKNOWLEDGED;

        getDataStore().merge(t, concern);
        return t;
    }


    @Override
    public T saveOrMerge(T t) {
        if (t.getId() == null) {
            setId(t);
            save(t);
        } else {
            update(t);
        }
        return t;
    }

    protected void setId(T entity) {

        if (entity.getId() == null) {
            String entityName = entity.getCollectionName();
            Long id = 1L;
            final Query<Sequence> q = getDataStore().createQuery(Sequence.class).field(Sequence.COLLECTION_NAME).equal(entityName);
            if (q.asList().isEmpty()) {
                getDataStore().save(new Sequence(entityName, id));
            } else {
                final UpdateOperations<Sequence> updateOperations = getDataStore().createUpdateOperations(Sequence.class)
                        .inc(Sequence.ID, 1);
                id = getDataStore().findAndModify(q, updateOperations).getId();


            }
            if (id != null) {
                entity.id(id);
            }


        }
    }

    public T save(T t) {

        if (t != null) {

            setId(t);
            getDataStore().save(t);
        }
        return t;
    }

    public Boolean isExists(T entity) {

        Query query = getDataStore().
                createQuery(entity.getClass()).field(ID).
                equal(entity.getId());

        long count = query.count();
        return count > 0;
    }
}

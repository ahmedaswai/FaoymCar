package com.car.fayoum.dao;

import com.car.fayoum.model.mongo.GenericEntity;
import com.car.fayoum.model.mongo.Sequence;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 * Created by ahmedissawi on 12/8/17.
 */
public abstract class GenericDao<T> implements IDao<T> {


    public static final String ID="id";
    public void setId(GenericEntity entity) {

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

    public GenericEntity save(GenericEntity t){
        if(t!=null){
            setId(t);
            getDataStore().save(t);
        }
        return t;
    }
    public Boolean isExists(T t){
        GenericEntity entity=(GenericEntity)t;
        Query query=getDataStore().
                createQuery(t.getClass()).field(ID).
                equal(entity.getId());

        long count=query.count();
        return count>0;
    }
}

package com.qcar.dao;

import com.qcar.model.mongo.GenericEntity;
import com.qcar.model.mongo.Sequence;
import com.mongodb.WriteConcern;
import com.qcar.model.mongo.User;
import com.qcar.service.cache.QCarCache;
import com.qcar.utils.CollectionUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;
import java.util.Optional;

/**
 * Created by ahmedissawi on 12/8/17.
 */
public abstract class GenericDao<T extends GenericEntity> implements IDao<T> {


    public static final String ID = "id";
    private final QCarCache cache;

    protected GenericDao(QCarCache cache) {
        this.cache = cache;
    }

    public Datastore getDataStore() {

       return DatabaseClient.INSTANCE.connect().db("qa-car").startScanning("com.qcar.model.mongo").
                datastore();

    }

    @Override
    public Boolean delete(T u) {
       return deleteById(u.getId());
    }

    @Override
    public Boolean deleteById(Long id) {
        Query query = getDataStore().
                createQuery(User.class).field(ID).
                equal(id);
        Boolean status= getDataStore().findAndDelete(query)!=null;
        if(status){
            getCache().removeItem(getEntity(),id);
        }
        return status;
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
        getCache().add(t);
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

            getCache().add(t);
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
    public List<T>findAll(){

        List<T>values=(List<T>)getCache().getAllByEntity(getEntity());
        if(values!=null&&!values.isEmpty()){
            return values;
        }

        Query<T> query = getDataStore().
                createQuery(getEntityClass());
        return query.asList();
    }


    public Optional<T> findById(Long id) {
        Optional<T> cached=(Optional<T>)getCache().get(id,getEntity());
        if(cached!=null){
            return cached;
        }
        Query<T> query = getDataStore().
                createQuery(getEntityClass()).field(ID).
                equal(id);
        FindOptions options = new FindOptions();


        options.limit(1);

        List<T> userList = query.asList(options);
        if (CollectionUtils.isEmpty(userList)) {
            throw new RuntimeException("User is not Existing");
        }
        T u = userList.get(0);
        return Optional.ofNullable(u);
    }

    public QCarCache getCache() {
        return cache;
    }

    abstract public Class<T> getEntityClass();
    abstract public T getEntity();



    public void populateCache(){
        cache.addToCache(getEntity(),findAll());
    }
}

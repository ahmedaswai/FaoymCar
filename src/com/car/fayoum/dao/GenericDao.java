package com.car.fayoum.dao;

import com.car.fayoum.model.mongo.GenericEntity;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.annotations.PrePersist;

/**
 * Created by ahmedissawi on 12/8/17.
 */
public abstract class GenericDao<T> implements IDao<T>{



    public void setId(T o) {
        GenericEntity entity=(GenericEntity)o;
        if(entity.getId()==null){
            String entityName=entity.getCollectionName();

            MongoDatabase db=getDataStore().getMongo().getDatabase("fayoum-car");
            String fun=String.format("getNextSequence('%s')",entityName);
            Document command=new Document();
            command.put("$eval", fun);
            command.put("nolock",true);
            Document doc=db.runCommand(command);
            Long id=doc.getDouble("retval").longValue();
            if(id!=null){
                entity.setId(id);
            }


        }
    }
}

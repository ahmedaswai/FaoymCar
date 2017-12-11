package com.car.fayoum.dao;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Created by ahmedissawi on 12/7/17.
 */
public enum  DatabaseClient {
    INSTANCE;

    private String startScanning;
    private MongoClient client;
    private String db;
    private final Morphia morphia = new Morphia();


    public DatabaseClient startScanning(String m){
        this.startScanning=m;

        return this;
    }
    public DatabaseClient db(String m){
        this.db=m;

        return this;
    }
    public DatabaseClient connect(String hostName,Integer port){
        client=new MongoClient(hostName,port);
        return this;
    }
    public DatabaseClient connect(){
        client=new MongoClient();
        return this;
    }
    public Datastore datastore(){
        morphia.mapPackage(startScanning);
        return morphia.createDatastore(client,db);
    }
}

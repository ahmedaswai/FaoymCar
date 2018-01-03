package com.qcar.service.cache;


import com.qcar.model.mongo.GenericEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class QCarCache {

    final Map<String,Map<Long,GenericEntity>> cache=new ConcurrentHashMap<>();

    Logger logger= LoggerFactory.getLogger(QCarCache.class);

    private Boolean useCache=true;
    private QCarCache(){

    }
    public void initCache(Boolean useCache){
        logger.info("Creating Cache Instance Cache Status {}",useCache);
        cache.clear();
        this.useCache=useCache;
    }

    public static final QCarCache instance(){
        return new QCarCache();
    }
    public <T extends GenericEntity>void addToCache(GenericEntity entity,List<T>lst){
        if  (!useCache )return ;

        logger.info("Adding List To Cache Instance  {}",entity.getCollectionName());

        Map<Long,GenericEntity>mp=lst.stream().
                                filter(x->x!=null)
                                .collect(Collectors.toMap(x->x.getId(),Function.identity()));
        cache.put(entity.getCollectionName(),mp);

    }

    public Optional<GenericEntity> get(Long id, GenericEntity entity){
        if  (!useCache )return Optional.ofNullable(null);

        logger.info("Getting from Cache Instance  {} with Id {} ",entity.getCollectionName(),id);

        GenericEntity cached=cache.get(entity.getCollectionName()).get(id);
        return Optional.ofNullable(cached);
    }

    public Boolean add(GenericEntity entity){
        if(entity==null||!useCache)return false;

        logger.info("Updating  Cache Instance  {} with Id {} ",entity.getCollectionName(),entity.getId());

        cache.get(entity.getCollectionName()).put(entity.getId(),entity);
        return true;
    }
    public List<String>getAllKeys(){
        return new ArrayList<>(cache.keySet());
    }
    public List<Map<Long,GenericEntity>>getAllValues(){
        return new ArrayList<>(cache.values());
    }
    public   List<GenericEntity>getAllByEntity(GenericEntity entity){
        if  (!useCache )return new ArrayList<>();

        logger.info("Getting from Cache Instance  List {}",entity.getCollectionName());

        return new ArrayList<>(cache.getOrDefault(entity.getCollectionName(),
                new HashMap<>()).values());

    }

    public Boolean removeItem(GenericEntity entity,Long id){
        logger.info("Removing from Cache Instance  List {} with Id{} ",entity.getCollectionName(),id);

        GenericEntity cachedEntity=cache.get(entity.getCollectionName()).get(id);
        if(cachedEntity!=null){
            cache.get(entity.getCollectionName()).remove(id);
        }
        return true;
    }

    public Boolean getUseCache() {
        return useCache;
    }
}

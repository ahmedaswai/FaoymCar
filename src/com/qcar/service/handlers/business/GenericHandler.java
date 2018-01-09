package com.qcar.service.handlers.business;

import com.qcar.dao.GenericDao;
import com.qcar.model.mongo.entity.GenericEntity;
import com.qcar.model.mongo.entity.User;
import com.qcar.model.mongo.embedded.ClientInfo;
import com.qcar.model.service.result.ServiceReturnList;
import com.qcar.model.service.result.ServiceReturnMap;
import com.qcar.model.service.result.ServiceReturnSingle;
import com.qcar.utils.CollectionUtils;
import com.qcar.utils.MediaType;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class GenericHandler<T extends GenericEntity> {
    GenericHandler(){}

    public abstract GenericDao<T>getDao();
    public void setClientInfo(T entity,RoutingContext ctx){

       User user=(User) ctx.data().get("user");
       entity.updatedOn(new Date())
               .updatedBy(user.getId()).
                clientInfo(ClientInfo.instance(ctx.request()));

    }
    public void findById(RoutingContext ctx){


        Long id = Long.parseLong(ctx.request().getParam("id"));

        T u = getDao().findById(id).get();
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void findAll(RoutingContext ctx){




        List<T> lst = getDao().findAll();

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }

    public void doDelete(RoutingContext ctx){


        Long id = Long.parseLong(ctx.request().getParam("id"));
        Buffer rs = ServiceReturnSingle.response(getDao().deleteById(id));

        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void doDeleteBulk(RoutingContext ctx){


        List<Long> ids = Json.decodeValue(ctx.getBody(), CollectionUtils.LONG_LIST_TYPE);
        Map<Long,Boolean> mp=ids.stream().collect(Collectors.toMap(Function.identity(),getDao()::deleteById));
        Buffer rs = ServiceReturnMap.response(mp);

        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }

    public void doAdd(RoutingContext ctx) {


        T entity = Json.decodeValue(ctx.getBody(), getDao().getEntityClass());

        setClientInfo(entity,ctx);

        Buffer rs = ServiceReturnSingle.response(getDao().saveOrMerge(entity));
        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }

}

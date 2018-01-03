package com.qcar.service.handlers.business;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.DriverDao;
import com.qcar.dao.UserDao;
import com.qcar.model.mongo.Driver;
import com.qcar.model.mongo.Location;
import com.qcar.model.mongo.User;
import com.qcar.model.service.ServiceReturnList;
import com.qcar.model.service.ServiceReturnMap;
import com.qcar.model.service.ServiceReturnSingle;
import com.qcar.utils.CollectionUtils;
import com.qcar.utils.MediaType;
import com.qcar.utils.SecurityUtils;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DriverHandler extends GenericHandler{
    final DriverDao dao;

    public DriverHandler(){
        dao = DaoFactory.driverDao();
    }
    public void findById(RoutingContext ctx){


        Long id = Long.parseLong(ctx.request().getParam("id"));

        Driver u = dao.findById(id).get();
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void findAll(RoutingContext ctx){


        List<Driver> lst = dao.findAll();

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void findInDistance(RoutingContext ctx){



        Double lng=Double.parseDouble(ctx.request().getParam("lng"));
        Double lat=Double.parseDouble(ctx.request().getParam("lat"));
        Double radius=Double.parseDouble(ctx.request().getParam("radius"));



        List<Driver> lst = dao.findInDistance(new Location(lng,lat),radius);

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void doAdd(RoutingContext ctx){



        Driver driver = Json.decodeValue(ctx.getBody(), Driver.class);

        setClientInfo(driver,ctx);

        Buffer rs = ServiceReturnSingle.response(dao.saveOrMerge(driver));
        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void doDelete(RoutingContext ctx){


        Long id = Long.parseLong(ctx.request().getParam("id"));
        Buffer rs = ServiceReturnSingle.response(dao.deleteById(id));

        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void doDeleteBulk(RoutingContext ctx){


        List<Long> ids = Json.decodeValue(ctx.getBody(), CollectionUtils.LONG_LIST_TYPE);
        Map<Long,Boolean> mp=ids.stream().collect(Collectors.toMap(Function.identity(),dao::deleteById));
        Buffer rs = ServiceReturnMap.response(mp);

        ctx.response().
                putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
    public void findAllActiveOnline(RoutingContext ctx){
        List<Driver> lst = dao.findAllActiveOnline();

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
    public void doActivate(RoutingContext ctx){

        Long id = Long.parseLong(ctx.request().getParam("id"));

        Driver u = dao.changeStatus(id,true);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
    public void doDeActivate(RoutingContext ctx){

        Long id = Long.parseLong(ctx.request().getParam("id"));

        Driver u = dao.changeStatus(id,false);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
}

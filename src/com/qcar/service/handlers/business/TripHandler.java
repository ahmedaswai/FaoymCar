package com.qcar.service.handlers.business;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.GenericDao;
import com.qcar.dao.TripDao;
import com.qcar.model.mongo.choicelist.TripStatus;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.mongo.entity.Trip;
import com.qcar.model.service.result.ServiceReturnList;
import com.qcar.model.service.result.ServiceReturnSingle;
import com.qcar.utils.MediaType;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.Optional;

public class TripHandler extends GenericHandler<Trip> {

    private final TripDao dao= DaoFactory.tripDao();
    @Override
    public GenericDao<Trip> getDao() {
        return dao;
    }
    public void findByTripNum(RoutingContext ctx){
        Long id = Long.parseLong(ctx.request().getParam("tripNum"));
        Trip o=dao.findByTripNumber(id).get();
        Buffer rs = ServiceReturnSingle.response(o);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);

    }
    public void findByTripStatus(RoutingContext ctx){
        Integer status = Integer.parseInt(ctx.request().getParam("tripStatus"));
        TripStatus tripStatus=TripStatus.getTripStatus(status);
        List<Trip> o=dao.findByTripStatus(tripStatus);
        Buffer rs = ServiceReturnList.response(o);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);

    }
    public void changeStatus(RoutingContext ctx){
        Trip trip = Json.decodeValue(ctx.getBody(), getDao().getEntityClass());
        Optional<Trip> o=dao.changeStatus(trip.getId(),trip.getStatus());
        Buffer rs = ServiceReturnSingle.response(o);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);

    }

}

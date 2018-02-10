package com.qcar.service.handlers.business;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.GenericDao;
import com.qcar.dao.TripDao;
import com.qcar.model.mongo.choicelist.TripStatus;
import com.qcar.model.mongo.embedded.Location;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.mongo.entity.Trip;
import com.qcar.model.mongo.search.OrderSearchCriteria;
import com.qcar.model.mongo.search.TripSearchCriteria;
import com.qcar.model.service.result.ServiceReturnList;
import com.qcar.model.service.result.ServiceReturnSingle;
import com.qcar.utils.GeneralUtils;
import com.qcar.utils.MediaType;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TripHandler extends GenericHandler<Trip> {

    private final TripDao dao= DaoFactory.tripDao();
    private final Double PRICE_PER_KILLO=5d;
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
    public void findByCriteria(RoutingContext ctx){



        MultiMap params=ctx.request().params();

        TripSearchCriteria criteria= TripSearchCriteria.instance(params);

        Buffer rs = ServiceReturnList.response(dao.findByCriteria(criteria));

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }

    public void calcTripCost(RoutingContext ctx){

        Trip trip = Json.decodeValue(ctx.getBody(), getDao().getEntityClass());

        int distance= GeneralUtils.calculateDistanceInKilometer(trip.getStartLoc(),trip.getEndLoc());
        Double waitingTimeCost=trip.getWaitingTime()*0.5;
        Double totalCost=waitingTimeCost+(distance*PRICE_PER_KILLO);
        Map<String,Double>mp=new HashMap<>();
        mp.put("totalEstimatedCost",totalCost);
        mp.put("totalEstimatedDistance",new Double(distance));
        mp.put("actualCost",totalCost);
        Buffer rs = ServiceReturnSingle.response(mp);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }
}

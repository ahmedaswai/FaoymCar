package com.qcar.service.handlers.business;

import com.qcar.dao.DaoFactory;
import com.qcar.dao.GenericDao;
import com.qcar.dao.OrderDao;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.service.ServiceReturnList;
import com.qcar.model.service.ServiceReturnSingle;
import com.qcar.utils.MediaType;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class OrderHandler extends GenericHandler<Order> {
    final OrderDao dao;

    public OrderHandler() {
        dao = DaoFactory.orderDao();
    }

    @Override
    public GenericDao<Order> getDao() {
        return dao;
    }

    public void findAllActive(RoutingContext ctx) {
        List<Order> lst = dao.findAllActive();

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }

    public void doActivate(RoutingContext ctx) {

        Long id = Long.parseLong(ctx.request().getParam("id"));

        Order u = dao.changeStatus(id, true);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }

    public void doDeActivate(RoutingContext ctx) {

        Long id = Long.parseLong(ctx.request().getParam("id"));

        Order u = dao.changeStatus(id, false);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }

    public void findByOrderNum(RoutingContext ctx){
        Long id = Long.parseLong(ctx.request().getParam("orderNum"));
        Order o=dao.findByOrderNumber(id).get();
        Buffer rs = ServiceReturnSingle.response(o);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);

    }

    public void findByCriteria(RoutingContext ctx){
        Buffer rs = ServiceReturnSingle.response("Not implemented Yet");

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
        //Long orderDateFrom=Long.parseLong(ctx.request().getParam("orderNum"));
    }
}

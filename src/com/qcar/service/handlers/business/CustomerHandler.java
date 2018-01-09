package com.qcar.service.handlers.business;

import com.qcar.dao.CustomerDao;
import com.qcar.dao.DaoFactory;
import com.qcar.dao.GenericDao;
import com.qcar.model.mongo.entity.Customer;
import com.qcar.model.service.result.ServiceReturnList;
import com.qcar.model.service.result.ServiceReturnSingle;
import com.qcar.utils.MediaType;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class CustomerHandler extends GenericHandler<Customer> {

    private final CustomerDao dao = DaoFactory.customerDao();

    @Override
    public GenericDao<Customer> getDao() {
        return dao;
    }

    public void findAllActive(RoutingContext ctx) {
        List<Customer> lst = dao.findAllActive();

        Buffer rs = ServiceReturnList.response(lst);

        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }

    public void doActivate(RoutingContext ctx) {

        Long id = Long.parseLong(ctx.request().getParam("id"));

        Customer u = dao.changeStatus(id, true);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }

    public void doDeActivate(RoutingContext ctx) {

        Long id = Long.parseLong(ctx.request().getParam("id"));

        Customer u = dao.changeStatus(id, false);
        Buffer rs = ServiceReturnSingle.response(u);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);
    }


}

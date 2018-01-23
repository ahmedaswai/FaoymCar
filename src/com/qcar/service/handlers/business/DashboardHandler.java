package com.qcar.service.handlers.business;

import com.qcar.dao.DatabaseClient;
import com.qcar.dao.DriverDao;
import com.qcar.dao.OrderDao;
import com.qcar.dao.TripDao;
import com.qcar.model.mongo.entity.Driver;
import com.qcar.model.mongo.entity.Order;
import com.qcar.model.mongo.entity.Trip;
import com.qcar.model.mongo.entity.User;
import com.qcar.model.service.result.DashboardResult;
import com.qcar.model.service.result.ServiceReturnSingle;
import com.qcar.utils.GeneralUtils;
import com.qcar.utils.MediaType;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;
import org.mongodb.morphia.Datastore;

import java.util.Date;

public class DashboardHandler{
    private final Datastore datastore;
   public DashboardHandler(){
       datastore =DatabaseClient.INSTANCE.datastore();

   }
    /* Show Dashboard Landing Page
    * We need to search for Orders in Specific Date
    * */
    public void   fetchDashboard(RoutingContext ctx) {
      Date beforeDateMonths= GeneralUtils.getDateBeforeMonths(3);
      long numberOfOnlineDrivers= datastore.getCount(datastore.createQuery(Driver.class).field(DriverDao.ONLINE).equal(true));
      long numberOfActiveDrivers= datastore.getCount(datastore.createQuery(Driver.class).field(DriverDao.STATUS).equal(true));
      long numberOfOnlineUsers= datastore.getCount(datastore.createQuery(User.class).field(DriverDao.ONLINE).equal(true));
      long numberOfActiveUsers= datastore.getCount(datastore.createQuery(User.class).field(DriverDao.STATUS).equal(true));
      long numberOfOrders=datastore.getCount(datastore.createQuery(Order.class).
              field(OrderDao.ORDER_DATE).greaterThanOrEq(beforeDateMonths));

      long numberOfTrips=datastore.getCount(datastore.createQuery(Trip.class).
              field(TripDao.ACTUAL_START_TIME).greaterThanOrEq(beforeDateMonths));


      DashboardResult dashboardResult= DashboardResult.instance().
              numberOfActiveDrivers(numberOfActiveDrivers).
              numberOfActiveUsers(numberOfActiveUsers).
              numberOfOnlineDrivers(numberOfOnlineDrivers).
              numberOfOnlineUsers(numberOfOnlineUsers).
              numberOfOrders(numberOfOrders).
              numberOfTrips(numberOfTrips);
        Buffer rs = ServiceReturnSingle.response(dashboardResult);
        ctx.response().putHeader("content-type", MediaType.APPLICATION_JSON)

                .setStatusCode(200).end(rs);


    }
}

package com.qcar.dao;

import com.qcar.model.mongo.entity.Order;
import com.qcar.service.cache.QCarCache;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OrderDao extends GenericDao<Order> implements IDao<Order>, IStatusDao{

    public static final String ORDER_NUM="orderNum";
    protected OrderDao(QCarCache cache) {
        super(cache);
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    public Order getEntity() {
        return Order.instance();
    }

    @Override
    public List<Order> findByExample(Order order) {
        return null;
    }
    public Order changeStatus(Long id, Boolean status) {

        return (Order) changeStatus(this, id, status, Order.class);
    }

    public Optional<Order> findByOrderNumber(Long orderId) {

        Order t = getDataStore().
                createQuery(getEntityClass()).field(ORDER_NUM).
                equal(orderId).get();

        return Optional.ofNullable(t);
    }
    @Override
    public Order save(Order order) {
        if (order != null) {
            setId(order);
            setOrderId(order);
            getDataStore().save(order);

        }
        return order;
    }

    private void setOrderId(Order order){
        Integer year= LocalDateTime.now().getYear();
        String id=order.getId().toString();
        StringBuilder idbt=new StringBuilder();
        for(int count=id.length();count<6;count++){
            idbt.append(0);
        }
        String orderNum=String.join("",year.toString(),idbt,id);
        order.orderNum(Long.parseLong(orderNum));
    }
    public List<Order> findAllActive() {

        return findAllActive(this, Order.class);
    }

}

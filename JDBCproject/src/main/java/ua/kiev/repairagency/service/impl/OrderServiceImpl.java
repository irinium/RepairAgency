package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void save(OrderEntity orderEntity) {
        if (orderEntity != null) {
            List<OrderEntity> orderEntities = orderDao.findAll();
            if (!orderEntities.isEmpty()) {
                OrderEntity lastOrderEntity = orderEntities.get(orderEntities.size() - 1);
                orderEntity.setId(lastOrderEntity.getId() + 1);
                orderDao.save(orderEntity);
            }
        }
    }

    public void delete(OrderEntity orderEntity) {
        if (orderEntity != null) {
            orderDao.deleteById(orderEntity.getId());
        }
    }

    public List<OrderEntity> getAll() {
        return orderDao.findAll();
    }
}

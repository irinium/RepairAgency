package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.order.OrderEntity;

import java.util.List;

public interface OrderService {
    void save(OrderEntity orderEntity);

    void delete(OrderEntity orderEntity);

    List<OrderEntity> getAll();

    OrderEntity getById(Integer id);
}

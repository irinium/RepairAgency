package ua.kiev.repairagency.repository;

import ua.kiev.repairagency.domain.order.OrderEntity;

import java.util.List;

public interface OrderRepository {
    void save(OrderEntity orderEntity);

    void delete(OrderEntity orderEntity);

    List<OrderEntity> getAll();
}

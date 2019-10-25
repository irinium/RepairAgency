package ua.kiev.repairagency.repository.dao;

import ua.kiev.repairagency.entity.order.OrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    void save(OrderEntity orderEntity);

    void deleteById(Long id);

    void update(OrderEntity orderEntity, Long price);

    List<OrderEntity> findAll();

    Optional <OrderEntity> findById(Long id);
}

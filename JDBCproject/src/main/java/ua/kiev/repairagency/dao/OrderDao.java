package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends GenericDao<OrderEntity, Long> {
    void save(OrderEntity orderEntity);

    void update(OrderEntity orderEntity, Long price);

    void update(OrderEntity orderEntity, Boolean state);

    void setMaster(OrderEntity orderEntity, Long masterId);

    List<OrderEntity> findAll(int currentPage, int recordsPerPage);

    List<OrderEntity> findUserOrders(UserEntity userEntity);

    Optional<OrderEntity> findById(Long id);
}

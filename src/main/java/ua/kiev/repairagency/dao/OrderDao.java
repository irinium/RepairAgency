package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends GenericDao<OrderEntity, Long> {
    int save(OrderEntity entity);

    Optional<OrderEntity> findById(Long id);

    List<OrderEntity> findAll(int currentPage, int recordsPerPage);

    List<OrderEntity> findUserOrders(UserEntity userEntity, int currentPage, int recordsPerPage);

    int getNumberOfRows() throws SQLException;

    void update(OrderEntity entity, String param);
    
    void updateByPrice(OrderEntity orderEntity, Double price);

    void updateByState(OrderEntity orderEntity, Boolean state);

    void updateByMaster(OrderEntity orderEntity, Long masterId);
}

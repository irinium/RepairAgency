package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.List;

public interface OrderDao extends GenericDao<OrderEntity, Long> {

    List<OrderEntity> findUserOrders(UserEntity userEntity, int currentPage, int recordsPerPage);

    List<OrderEntity> findMastersOrders(UserEntity userEntity, int currentPage, int recordsPerPage);

    List<OrderEntity> findOrdersWithoutMaster(int currentPage, int recordsPerPage);

    int getNumberOfRows();

    void update(OrderEntity entity, String param);

    void updateByPrice(OrderEntity orderEntity, Double price);

    void updateByState(OrderEntity orderEntity, Boolean state);

    void updateByStatus(OrderEntity entity, Boolean status);

    void updateByMaster(OrderEntity orderEntity, Long masterId);
}

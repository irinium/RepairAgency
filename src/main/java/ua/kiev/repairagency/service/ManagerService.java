package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.order.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface ManagerService {

     Optional<User> findById(Long id);

     List<User> findAll();

    Optional findUserByEmail(String email);

    void acceptOrder(Order order);

    void rejectOrder(Order order);

    void setCommentsToRejectedOrder(OrderEntity entity, String message);

    void setPrice(Order order, Long price);
}

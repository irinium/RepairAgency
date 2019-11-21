package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;

public interface ManagerService extends UserGenericService{
    User findUserById(Long id);

    Order findOrderById(Long id);

    User findUserByEmail(String email);

    void acceptOrder(Order order);

    void rejectOrder(Order order);

    void setCommentsToRejectedOrder(Order order, String message);

    void setPrice(Order order, Double price);
}

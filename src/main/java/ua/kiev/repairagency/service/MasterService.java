package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;

import java.util.List;

public interface MasterService extends UserGenericService{
    void acceptOrder(Order order, User master);

    void completeOrder(Order order);

    void updatePassword(User user, String password);

    List<Order> findMastersOrders(User user, int currentPage, int recordsPerPage);
}

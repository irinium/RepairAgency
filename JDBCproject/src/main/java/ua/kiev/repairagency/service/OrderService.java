package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.order.Order;

import java.util.List;

public interface OrderService {
    void save(Order orderEntity);

    List<Order> getAll(int currentPage, int recordsPerPage);

    void update(Order order, Boolean state);

    void update(Order order, Long price);
}

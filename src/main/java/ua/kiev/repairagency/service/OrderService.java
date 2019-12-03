package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;

import java.util.List;

public interface OrderService  {

    List<Order> getAll(int currentPage, int recordsPerPage);

    List<Order> getOrdersWithoutMaster(int currentPage, int recordsPerPage);

    int getNumberOfOrdersRows();

    int getNumberOfUserOrdersRows(User customer);

    int getNumberOfMasterOrdersRows(User master);

    int getNumberOfOrdersRowsWithoutMaster();

    int getNumberOfResponsesRows();

    List<Response> getAllResponses(int currentPage, int recordsPerPage);

    void update(Order order, Boolean state);

    void update(Order order, Double price);
}

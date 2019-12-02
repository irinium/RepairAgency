package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;

import java.util.List;

public interface CustomerService extends UserGenericService {
    void makeOrder(Appliance appliance, User user, String title);

    List<Order> findAllOrders(User user, int currentPage, int recordsPerPage);

    void createResponse(Response response);
}

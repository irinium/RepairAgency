package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.appliance.ElectricAppliance;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;

import java.util.List;

public interface CustomerService {
    User register(User customer);

    User login(String email, String password);

    int makeOrder(ElectricAppliance appliance, User user, String title);

    List findAllOrders(User user, int currentPage, int recordsPerPage);

    public int createResponse(Response response);
}

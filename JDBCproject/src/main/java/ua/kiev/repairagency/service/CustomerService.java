package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.appliance.ElectricAppliance;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.Customer;
import ua.kiev.repairagency.domain.user.User;

import java.util.List;

public interface CustomerService {

    void makeOrder(ElectricAppliance appliance, Customer customer, String title);

    List findAllOrders(User user);

    public Integer createResponse(Response response);
}

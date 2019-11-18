package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;

public interface MasterService {

    User login(String email, String password);

    void acceptOrder(Order order, User master);

    void updatePassword(User user, String password);
}

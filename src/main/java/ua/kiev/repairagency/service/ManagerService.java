package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    User register(User master);

    User login(String login, String password);

    Optional<User> findUserById(Long id);

    Optional<Order> findOrderById(Long id);

    List<User> findAll(int currentPage, int recordsPerPage);

    Optional findUserByEmail(String email);

    void updatePassword(User user, String password);

    void acceptOrder(Order order);

    void rejectOrder(Order order);

    void setCommentsToRejectedOrder(Order order, String message);

    void setPrice(Order order, Double price);
}

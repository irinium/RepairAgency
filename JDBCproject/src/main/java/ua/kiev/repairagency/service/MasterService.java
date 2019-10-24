package ua.kiev.repairagency.service;

import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.MasterEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

public interface MasterService {
    void register(MasterEntity master);

    UserEntity login(String email, String password);

    void acceptOrder(OrderEntity order);
}

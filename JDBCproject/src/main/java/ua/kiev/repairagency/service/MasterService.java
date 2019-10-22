package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.order.OrderEntity;
import ua.kiev.repairagency.domain.user.ManagerEntity;
import ua.kiev.repairagency.domain.user.UserEntity;

public interface MasterService {
    void register(ManagerEntity managerEntity);

    UserEntity login(String email, String password);

    void acceptOrder(OrderEntity order);
}

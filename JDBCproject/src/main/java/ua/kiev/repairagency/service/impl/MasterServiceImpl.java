package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.MasterEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.MasterService;

public class MasterServiceImpl implements MasterService {

    @Override
    public UserEntity login(String email, String password) {
        return null;
    }

    @Override
    public void acceptOrder(OrderEntity order) {
    }
}

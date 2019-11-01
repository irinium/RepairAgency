package ua.kiev.repairagency.service;

import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.CustomerEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.List;

public interface CustomerService {

    void makeOrder(ElectricApplianceEntity applianceEntity, UserEntity userEntity, String title);

    List findAllOrders(UserEntity userEntity);
}

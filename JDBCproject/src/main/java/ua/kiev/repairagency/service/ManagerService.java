package ua.kiev.repairagency.service;

import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.CustomerEntity;
import ua.kiev.repairagency.entity.user.ManagerEntity;
import ua.kiev.repairagency.entity.user.MasterEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ManagerService {

    public Optional<UserEntity> findById(Long id);

    public void deleteById(Long id);

    public List<UserEntity> findAll();

    Optional findUserByEmail(String email);

    void acceptOrder(OrderEntity orderEntity, ManagerEntity managerEntity, CustomerEntity customerEntity);

    void rejectOrder(OrderEntity orderEntity, ManagerEntity managerEntity, String reason, CustomerEntity customerEntity);

    void setPrice(OrderEntity order, Long price);
}

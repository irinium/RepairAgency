package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.CustomerEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.exception.EntityNotFoundException;
import ua.kiev.repairagency.validator.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


public class CustomerServiceImpl extends GenericService<CustomerEntity> implements CustomerService {
    private final UserDao userDao;
    private final OrderDao orderDao;
    private final ApplianceDao applianceDao;

    public CustomerServiceImpl(UserDao userDao,
                               OrderDao orderDao,
                               ApplianceDao applianceDao) {
        super();
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.applianceDao = applianceDao;
    }

    public void register(CustomerEntity customerEntity) {
       super.register(customerEntity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserEntity login(String email, String password) {
        return super.login(email, password);
    }

    @Override
    public void makeOrder(ElectricApplianceEntity applianceEntity, UserEntity userEntity, String title) {
        orderDao.save(new OrderEntity(applianceEntity.getId(), applianceEntity.getId(), 0L, userEntity.getId(), 0L, title));
    }

    @Override
    public List findAllOrders(UserEntity userEntity) {
        return orderDao.findUserOrders(userEntity);
    }

    public void createResponse(String response){

    }
}

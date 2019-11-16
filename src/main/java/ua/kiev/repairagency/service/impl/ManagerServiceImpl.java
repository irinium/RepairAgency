package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import java.util.List;
import java.util.Optional;

public class ManagerServiceImpl extends UserGenericService implements ManagerService {
    private final ApplianceDao applianceDao;
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;



    public ManagerServiceImpl(UserDao userDao,
                              ApplianceDao applianceDao,
                              OrderDao orderDao,
                              PasswordEncoder passwordEncoder,
                              Validator validator, UserMapper userMapper, OrderMapper orderMapper) {
        super(passwordEncoder,userDao, validator,userMapper);
        this.applianceDao = applianceDao;
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<User> findAll() {
        return findAll();
    }

    public User register(User master) {
        return super.register(master);
    }

    @Override
    public User login(String login, String password) {
        return super.login(login, password);
    }

    @Override
    public void update(User user, String password) {
        update(user,password);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id).map(userMapper::mapUserEntityToUser);
    }

    public Optional<Order> findOrderById(Long id) {
        return orderDao.findById(id).map(orderMapper::mapOrderEntityToOrder);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDao.findByEmail(email).map(userMapper::mapUserEntityToUser);
    }

    @Override
    public void setPrice(Order order, Long price) {
        orderDao.updateByPrice(orderMapper.mapOrderToOrderEntity(order), price);
    }

    @Override
    public void acceptOrder(Order order) {
        orderDao.updateByState(orderMapper.mapOrderToOrderEntity(order),true);
    }

    @Override
    public void rejectOrder(Order order) {
        orderDao.updateByState(orderMapper.mapOrderToOrderEntity(order),false);
    }

    @Override
    public void setCommentsToRejectedOrder(OrderEntity entity, String message) {
        orderDao.update(entity, message);
    }
}


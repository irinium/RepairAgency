package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.encoder.PasswordEncoder;
import ua.kiev.repairagency.service.exception.EntityNotFoundException;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.UserValidator;

public class ManagerServiceImpl extends UserGenericServiceImpl implements ManagerService {
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;

    public ManagerServiceImpl(UserDao userDao,
                              OrderDao orderDao,
                              PasswordEncoder passwordEncoder,
                              UserValidator userValidator,
                              UserMapper userMapper,
                              OrderMapper orderMapper) {
        super(passwordEncoder, userDao, userValidator, userMapper);
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findById(id).map(userMapper::mapUserEntityToUser)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Order findOrderById(Long id) {
        return orderDao.findById(id).map(orderMapper::mapOrderEntityToOrder)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findByEmail(email).map(userMapper::mapUserEntityToUser)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void setPrice(Order order, Double price) {
        orderDao.updateByPrice(orderMapper.mapOrderToOrderEntity(order), price);
    }

    @Override
    public void acceptOrder(Order order) {
        orderDao.updateByState(orderMapper.mapOrderToOrderEntity(order), true);
    }

    @Override
    public void rejectOrder(Order order) {
        orderDao.updateByState(orderMapper.mapOrderToOrderEntity(order), false);
    }

    @Override
    public void setCommentsToRejectedOrder(Order order, String message) {
        orderDao.update(orderMapper.mapOrderToOrderEntity(order), message);
    }
}


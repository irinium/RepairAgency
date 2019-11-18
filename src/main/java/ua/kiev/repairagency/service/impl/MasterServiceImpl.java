package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.MasterService;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

public class MasterServiceImpl extends UserGenericService implements MasterService {
    private final OrderMapper orderMapper;
    private final OrderDao orderDao;

    public MasterServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao, Validator validator, UserMapper userMapper, OrderMapper orderMapper, OrderDao orderDao) {
        super(passwordEncoder, userDao, validator, userMapper);
        this.orderMapper = orderMapper;
        this.orderDao = orderDao;
    }

    @Override
    public User login(String email, String password) {
        return super.login(email, password);
    }

    @Override
    public void acceptOrder(Order order, User master) {
        orderDao.updateByMaster(orderMapper.mapOrderToOrderEntity(order), master.getId());
    }

    public void updatePassword(User user, String password) {
        super.updatePassword(user, password);
    }
}

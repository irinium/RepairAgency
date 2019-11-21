package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.MasterService;
import ua.kiev.repairagency.service.encoder.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.UserValidator;

import java.util.List;
import java.util.stream.Collectors;

public class MasterServiceImpl extends UserGenericServiceImpl implements MasterService {
    private final OrderMapper orderMapper;
    private final OrderDao orderDao;

    public MasterServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao, UserValidator userValidator,
                             UserMapper userMapper, OrderMapper orderMapper, OrderDao orderDao) {
        super(passwordEncoder, userDao, userValidator, userMapper);
        this.orderMapper = orderMapper;
        this.orderDao = orderDao;
    }

    @Override
    public void acceptOrder(Order order, User master) {
        orderDao.updateByMaster(orderMapper.mapOrderToOrderEntity(order), master.getId());
    }

    public void completeOrder(Order order){
        orderDao.updateByStatus(orderMapper.mapOrderToOrderEntity(order),false);
    }

    public List<Order> findMastersOrders(User user, int currentPage, int recordsPerPage) {
        return orderDao.findMastersOrders(userMapper.mapUserToUserEntity(user), currentPage, recordsPerPage).stream()
                .map(orderMapper::mapOrderEntityToOrder)
                .collect(Collectors.toList());
    }
}

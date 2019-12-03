package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.ResponseDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.OrderService;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ResponseDao responseDao;
    private final OrderMapper orderMapper;
    private final ResponseMapper responseMapper;
    private final UserMapper userMapper;

    public OrderServiceImpl(OrderDao orderDao, OrderMapper orderMapper,
                            ResponseDao responseDao, ResponseMapper responseMapper, UserMapper userMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
        this.responseDao = responseDao;
        this.responseMapper = responseMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<Order> getAll(int currentPage, int recordsPerPage) {
        return orderDao.findAll(currentPage, recordsPerPage).stream()
                .map(orderMapper::mapOrderEntityToOrder)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrdersWithoutMaster(int currentPage, int recordsPerPage) {
        return orderDao.findOrdersWithoutMaster(currentPage, recordsPerPage).stream()
                .map(orderMapper::mapOrderEntityToOrder)
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfOrdersRows() {
        return orderDao.getNumberOfRows();
    }

    @Override
    public int getNumberOfUserOrdersRows(User user) {
        return orderDao.getNumberOfUserOrdersRows(userMapper.mapUserToUserEntity(user));
    }

    @Override
    public int getNumberOfMasterOrdersRows(User master) {
        return orderDao.getNumberOfMasterOrdersRows(userMapper.mapUserToUserEntity(master));
    }

    @Override
    public int getNumberOfOrdersRowsWithoutMaster() {
        return orderDao.numberOfOrdersWithoutMasterRows();
    }

    @Override
    public int getNumberOfResponsesRows() {
        return responseDao.getNumberOfRows();
    }

    @Override
    public List<Response> getAllResponses(int currentPage, int recordsPerPage) {
        return responseDao.findAll(currentPage, recordsPerPage).stream()
                .map(responseMapper::mapResponseEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Order order, Boolean state) {
        orderDao.updateByState(orderMapper.mapOrderToOrderEntity(order), state);
    }

    @Override
    public void update(Order order, Double price) {
        orderDao.updateByPrice(orderMapper.mapOrderToOrderEntity(order), price);
    }
}

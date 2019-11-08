package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.OrderService;
import ua.kiev.repairagency.service.exception.EmptyDataException;
import ua.kiev.repairagency.service.mapper.OrderMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderDao orderDao, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }

    public void save(Order order) {
        orderDao.save(Optional.ofNullable(orderMapper
                .mapOrderToOrderEntity(order))
                .orElseThrow(() -> new EmptyDataException("Empty data set!")));
    }

    public List<Order> getAll(int currentPage, int recordsPerPage) {
        return orderDao.findAll(currentPage,recordsPerPage).stream()
                .map(orderMapper::mapOrderEntityToOrder)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Order order, Boolean state) {
        orderDao.update(orderMapper.mapOrderToOrderEntity(order), state);
    }

    @Override
    public void update(Order order, Long price) {
        orderDao.update(orderMapper.mapOrderToOrderEntity(order), price);
    }
}

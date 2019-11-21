package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.ResponseDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.service.OrderService;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;
    private final ResponseDao responseDao;
    private final ResponseMapper responseMapper;

    public OrderServiceImpl(OrderDao orderDao, OrderMapper orderMapper, ResponseDao responseDao, ResponseMapper responseMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
        this.responseDao = responseDao;
        this.responseMapper = responseMapper;
    }

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

    public int getNumberOfOrdersRows() {
       return orderDao.getNumberOfRows();
    }

    public int getNumberOfResponsesRows() {
        return responseDao.getNumberOfRows();
    }

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

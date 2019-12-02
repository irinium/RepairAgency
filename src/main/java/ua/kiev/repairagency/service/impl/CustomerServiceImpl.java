package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.dao.impl.ResponseDaoImpl;
import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.encoder.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.UserValidator;

import java.util.List;
import java.util.stream.Collectors;


public class CustomerServiceImpl extends UserGenericServiceImpl implements CustomerService {
    private final OrderDao orderDao;
    private final OrderMapper orderMapper;
    private final ResponseDaoImpl responseDao;
    private final ResponseMapper responseMapper;

    public CustomerServiceImpl(UserDao userDao,
                               OrderDao orderDao,
                               PasswordEncoder passwordEncoder,
                               UserValidator userValidator,
                               UserMapper userMapper, OrderMapper orderMapper, ResponseDaoImpl responseDao, ResponseMapper responseMapper) {
        super(passwordEncoder, userDao, userValidator, userMapper);
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
        this.responseDao = responseDao;
        this.responseMapper = responseMapper;
    }

    @Override
    public void makeOrder(Appliance appliance, User customer, String title) {
        Order order = Order.builder()
                .withAppliance(appliance)
                .withCustomer(customer)
                .withTitle(title)
                .withStatus(true)
                .build();
        orderDao.save(orderMapper.mapOrderToOrderEntity(order));
    }

    @Override
    public List<Order> findAllOrders(User user, int currentPage, int recordsPerPage) {
        return orderDao.findUserOrders(userMapper.mapUserToUserEntity(user), currentPage, recordsPerPage).stream()
                .map(orderMapper::mapOrderEntityToOrder)
                .collect(Collectors.toList());
    }

    @Override
    public void createResponse(Response response) {
        responseDao.save(responseMapper.mapResponseToResponseEntity(response));
    }
}

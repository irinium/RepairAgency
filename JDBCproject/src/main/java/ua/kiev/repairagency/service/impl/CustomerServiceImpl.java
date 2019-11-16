package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.dao.impl.ResponseDaoImpl;
import ua.kiev.repairagency.domain.appliance.ElectricAppliance;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import java.util.List;


public class CustomerServiceImpl extends UserGenericService implements CustomerService {
    private final OrderDao orderDao;
    private final ApplianceDao applianceDao;
    private final OrderMapper orderMapper;
    private final ResponseDaoImpl responseDao;
    private final ResponseMapper responseMapper;

    public CustomerServiceImpl(UserDao userDao,
                               OrderDao orderDao,
                               ApplianceDao applianceDao,
                               PasswordEncoder passwordEncoder,
                               Validator validator,
                               UserMapper userMapper, OrderMapper orderMapper, ResponseDaoImpl responseDao, ResponseMapper responseMapper) {
        super(passwordEncoder, userDao, validator, userMapper);
        this.orderDao = orderDao;
        this.applianceDao = applianceDao;
        this.orderMapper = orderMapper;
        this.responseDao = responseDao;
        this.responseMapper = responseMapper;
    }

    public User register(User customer) {
        return super.register(customer);
    }

    @Override
    public User login(String email, String password) {
        return super.login(email, password);
    }

    @Override
    public void makeOrder(ElectricAppliance appliance, User customer, String title) {
        orderDao.save(orderMapper.mapOrderToOrderEntity(new Order.OrderBuilder()
                .withAppliance(appliance)
                .withCustomer(customer)
                .withTitle(title)
                .build()));
    }

    @Override
    public List findAllOrders(User user,int currentPage, int recordsPerPage) {
        return orderDao.findUserOrders(userMapper.mapUserToUserEntity(user),currentPage,recordsPerPage);
    }

    @Override
    public Integer createResponse(Response response) {
        return responseDao.save(responseMapper.mapResponseToResponseEntity(response));
    }
}

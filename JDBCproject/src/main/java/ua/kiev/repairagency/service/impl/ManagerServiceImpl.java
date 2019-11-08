package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.Customer;
import ua.kiev.repairagency.domain.user.Manager;
import ua.kiev.repairagency.domain.user.Master;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import java.util.List;
import java.util.Optional;

public class ManagerServiceImpl extends UserGenericService<Manager> implements ManagerService {
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

    public void register(Master master) {
        super.register(master);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Manager login(String login, String password) {
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
        orderDao.update(orderMapper.mapOrderToOrderEntity(order), price);
    }

    @Override
    public void acceptOrder(Order order) {
        orderDao.update(orderMapper.mapOrderToOrderEntity(order),true);
    }

    @Override
    public void rejectOrder(Order order) {
        orderDao.update(orderMapper.mapOrderToOrderEntity(order),false);
    }

    @Override
    public void sendMessage(String message) {

    }

    private String acceptMessage(Customer customer, Manager manager, Order order) {
        return "Dear, " + customer.getName() + ",\n" +
                "I'm a manager of Repair Agency. Thank you for your interesting in our company. " +
                "Your order is " + order + "\n" +
                "We appreciate your contacting us. Please write to us anytime if you need help.\n" +
                "Sincerely, Repair Agency\n" + manager.getEmail() + " " + manager.getPhone();
    }

    private String rejectMessage(Customer customer, Manager manager, String reason) {
        return "Dear, " + customer.getName() + ",\n" +
                "I'm a manager of Repair Agency. Thank you for your interesting in our company. " +
                "Unfortunately, we won't be able to accommodate your request for repair yor appliance, because " + reason +
                "\n" +
                "We appreciate your contacting us. We value your business and look forward to serving you." +
                " Please write to us anytime if you need help with another request in the future.\n" +
                "Sincerely, Repair Agency\n" + manager.getEmail() + " " + manager.getPhone();
    }
}


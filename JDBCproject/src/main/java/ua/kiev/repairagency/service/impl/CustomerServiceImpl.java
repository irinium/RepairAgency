package ua.kiev.repairagency.service.impl;

import ua.kiev.repairagency.repository.dao.ApplianceDao;
import ua.kiev.repairagency.repository.dao.OrderDao;
import ua.kiev.repairagency.repository.dao.UserDao;
import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.CustomerEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.PasswordEncoderImpl;
import ua.kiev.repairagency.service.exception.EntityNotFoundException;
import ua.kiev.repairagency.validator.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


public class CustomerServiceImpl implements CustomerService {
    private final UserDao userDao;
    private final Validator validator;
    private final PasswordEncoderImpl passwordEncoderImpl;
    private final OrderDao orderDao;
    private final ApplianceDao applianceDao;

    public CustomerServiceImpl(UserDao userDao,
                               Validator validator,
                               PasswordEncoderImpl passwordEncoder,
                               OrderDao orderDao,
                               ApplianceDao applianceDao) {
        this.userDao = userDao;
        this.validator = validator;
        this.passwordEncoderImpl = passwordEncoder;
        this.orderDao = orderDao;
        this.applianceDao = applianceDao;
    }

    @Override
    public CustomerEntity register(CustomerEntity customerEntity) {
        try {
            validator.validate(customerEntity);
            if (!userDao.findByEmail(customerEntity.getEmail()).isPresent()) {
                String password = customerEntity.getPassword();
                String encode = passwordEncoderImpl.encode(password);
                userDao.save(customerEntity);
            } else {
                System.out.println("User with this login is already registered");
            }
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }
        return customerEntity;
    }

    @Override
    public UserEntity login(String login, String password) {
        String encoder = passwordEncoderImpl.encode(password);
        CustomerEntity customerEntity = (CustomerEntity) userDao.findByEmail(login)
                .orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
        String customerPassword = customerEntity.getPassword();
        if (passwordEncoderImpl.matches(customerPassword, encoder)) {
            return customerEntity;
        }
        throw new EntityNotFoundException("");
    }

    @Override
    public List<? extends ApplianceEntity> allAppliances() {
        return applianceDao.findAll();
    }

    @Override
    public List<ElectricApplianceEntity> filterByPowerConsumption() {
        return applianceDao.findAll().stream()
                .sorted(Comparator.comparing(ElectricApplianceEntity::getPowerConsumption))
                .collect(toList());
    }

    @Override
    public List<ElectricApplianceEntity> filterByManufacturer() {
        return applianceDao.findAll().stream()
                .sorted(Comparator.comparing(ApplianceEntity::getManufacturerEntity))
                .collect(toList());
    }

    @Override
    public List<ElectricApplianceEntity> filterByType() {
        return applianceDao.findAll().stream()
                .sorted(Comparator.comparing(ApplianceEntity::getTypeEntity))
                .collect(toList());
    }

    @Override
    public void makeOrder(ElectricApplianceEntity applianceEntity) {
        orderDao.save(new OrderEntity(applianceEntity.getId(),
                applianceEntity.toString()));
    }
}

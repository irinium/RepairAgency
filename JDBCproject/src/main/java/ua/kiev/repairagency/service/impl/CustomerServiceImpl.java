package ua.kiev.appliances.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.appliances.domain.appliance.ApplianceEntity;
import ua.kiev.appliances.domain.appliance.ElectricApplianceEntity;
import ua.kiev.appliances.domain.order.OrderEntity;
import ua.kiev.appliances.domain.user.CustomerEntity;
import ua.kiev.appliances.domain.user.UserEntity;
import ua.kiev.appliances.repository.ApplianceRepository;
import ua.kiev.appliances.repository.OrderRepository;
import ua.kiev.appliances.repository.UserRepository;
import ua.kiev.appliances.service.CustomerService;
import ua.kiev.appliances.service.PasswordEncoderImpl;
import ua.kiev.appliances.service.exception.EntityNotFoundException;
import ua.kiev.appliances.validator.Validator;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final UserRepository userRepository;
    private final Validator validator;
    private final PasswordEncoderImpl passwordEncoderImpl;
    private final OrderRepository orderRepository;
    private final ApplianceRepository applianceRepository;

    @Autowired
    public CustomerServiceImpl(UserRepository userRepository,
                               Validator validator,
                               PasswordEncoderImpl passwordEncoder,
                               OrderRepository orderRepository,
                               ApplianceRepository applianceRepository) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.passwordEncoderImpl = passwordEncoder;
        this.orderRepository = orderRepository;
        this.applianceRepository = applianceRepository;
    }

    @Override
    public CustomerEntity register(CustomerEntity customerEntity) {
        try {
            validator.validate(customerEntity);
            if (userRepository.findByEmail(customerEntity.getEmail()) == null) {
                String password = customerEntity.getPassword();
                String encode = passwordEncoderImpl.encode(password);
                userRepository.save(customerEntity);
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
        UserEntity userEntity = userRepository.findByEmail(login);
        if (userEntity == null) {
            throw new EntityNotFoundException("EntityNotFound");
        }else {
            String studentPassword = userEntity.getPassword();
            if (passwordEncoderImpl.matches(studentPassword, encoder)) {
                return userEntity;
            }
            throw new EntityNotFoundException("");
        }
    }

    @Override
    public List<? extends ApplianceEntity> allAppliances() {
        return applianceRepository.getAllAppliances();
    }

    @Override
    public List<ElectricApplianceEntity> filterByPowerConsumption() {
        return applianceRepository.getAllAppliances().stream()
                .sorted(Comparator.comparing(ElectricApplianceEntity::getPowerConsumption))
                .collect(toList());
    }

    @Override
    public List<ElectricApplianceEntity> filterByManufacturer() {
        return applianceRepository.getAllAppliances().stream()
                .sorted(Comparator.comparing(ApplianceEntity::getManufacturerEntity))
                .collect(toList());
    }

    @Override
    public List<ElectricApplianceEntity> filterByType() {
        return applianceRepository.getAllAppliances().stream()
                .sorted(Comparator.comparing(ApplianceEntity::getTypeEntity))
                .collect(toList());
    }

    @Override
    public void makeOrder(ElectricApplianceEntity applianceEntity) {
        orderRepository.save(new OrderEntity(applianceEntity.getId(),
                applianceEntity.toString(), applianceEntity.getPrice()));
    }

    public ElectricApplianceEntity findById(Long id) {
        return applianceRepository.findById(id);
    }
}

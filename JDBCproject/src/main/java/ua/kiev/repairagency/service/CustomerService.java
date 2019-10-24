package ua.kiev.repairagency.service;

import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.user.CustomerEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.List;

public interface CustomerService {

    CustomerEntity register(CustomerEntity customerEntity);

    UserEntity login(String login, String password);

    List<? extends ApplianceEntity> allAppliances();

    List<ElectricApplianceEntity> filterByPowerConsumption();

    List<ElectricApplianceEntity> filterByManufacturer();

    List<ElectricApplianceEntity> filterByType();

    void makeOrder(ElectricApplianceEntity applianceEntity);

}

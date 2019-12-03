package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.appliance.Manufacturer;
import ua.kiev.repairagency.domain.appliance.Type;
import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ManufacturerEntity;
import ua.kiev.repairagency.entity.appliance.TypeEntity;

public class ApplianceMapper {
    private final UserMapper userMapper;

    public ApplianceMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Appliance mapApplianceEntityToAppliance(ApplianceEntity applianceEntity) {
        return Appliance.builder()
                .withId(applianceEntity.getId())
                .withName(applianceEntity.getName())
                .withModel(applianceEntity.getModel())
                .withManufacturer(Manufacturer.valueOf(applianceEntity.getManufacturerEntity().name()))
                .withType(Type.valueOf(applianceEntity.getTypeEntity().name()))
                .withDisrepair(applianceEntity.getDisrepair())
                .withUser(userMapper.mapUserEntityToUser(applianceEntity.getUserEntity()))
                .build();
    }

    public ApplianceEntity mapApplianceToApplianceEntity(Appliance appliance) {
        return ApplianceEntity.builder()
                .withId(appliance.getId())
                .withName(appliance.getName())
                .withModel(appliance.getModel())
                .withManufacturer(ManufacturerEntity.valueOf(appliance.getManufacturer().name()))
                .withType(TypeEntity.valueOf(appliance.getType().name()))
                .withDisrepair(appliance.getDisrepair())
                .withUser(userMapper.mapUserToUserEntity(appliance.getUser()))
                .build();
    }
}

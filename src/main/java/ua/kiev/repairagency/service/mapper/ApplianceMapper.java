package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.appliance.Manufacturer;
import ua.kiev.repairagency.domain.appliance.Type;
import ua.kiev.repairagency.entity.appliance.ApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ManufacturerEntity;
import ua.kiev.repairagency.entity.appliance.TypeEntity;

import java.util.Optional;

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
                .withManufacturer(Optional
                        .of(Manufacturer.valueOf(applianceEntity.getManufacturerEntity().name()))
                        .orElse(null))
                .withType(Optional.of(Type.valueOf(applianceEntity.getTypeEntity().name())).orElse(null))
                .withDisrepair(applianceEntity.getDisrepair())
                .withUser(userMapper.mapUserEntityToUser(applianceEntity.getUserEntity()))
                .build();
    }

    public ApplianceEntity mapApplianceToApplianceEntity(Appliance appliance) {
        return ApplianceEntity.builder()
                .withId(appliance.getId())
                .withName(appliance.getName())
                .withModel(appliance.getModel())
                .withManufacturer(Optional
                        .of(ManufacturerEntity.valueOf(appliance.getManufacturer().name()))
                        .orElse(null))
                .withType(Optional.of(TypeEntity.valueOf(appliance.getType().name())).orElse(null))
                .withDisrepair(appliance.getDisrepair())
                .withUser(userMapper.mapUserToUserEntity(appliance.getUser()))
                .build();
    }
}

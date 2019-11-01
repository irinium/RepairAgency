package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.appliance.ElectricAppliance;
import ua.kiev.repairagency.domain.appliance.Manufacturer;
import ua.kiev.repairagency.domain.appliance.Type;
import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ManufacturerEntity;
import ua.kiev.repairagency.entity.appliance.TypeEntity;

public class ApplianceMapper {
    public ElectricAppliance mapApplianceEntityToAppliance(ElectricApplianceEntity applianceEntity) {
        return new ElectricAppliance.ElectricApplianceBuilder()
                .withId(applianceEntity.getId())
                .withName(applianceEntity.getName())
                .withModel(applianceEntity.getModel())
                .withManufacturer(Manufacturer.valueOf(applianceEntity.getManufacturerEntity().name()))
                .withType(Type.valueOf(applianceEntity.getTypeEntity().name()))
                .withPowerConsumption(applianceEntity.getPowerConsumption())
                .withDisrepair(applianceEntity.getDisrepair())
                .build();
    }

    public ElectricApplianceEntity mapApplianceToApplianceEntity(ElectricAppliance appliance) {
        return new ElectricApplianceEntity.ElectricApplianceBuilder()
                .withId(appliance.getId())
                .withName(appliance.getName())
                .withModel(appliance.getModel())
                .withManufacturer(ManufacturerEntity.valueOf(appliance.getManufacturer().name()))
                .withType(TypeEntity.valueOf(appliance.getType().name()))
                .withPowerConsumption(appliance.getPowerConsumption())
                .withDisrepair(appliance.getDisrepair())
                .build();
    }
}

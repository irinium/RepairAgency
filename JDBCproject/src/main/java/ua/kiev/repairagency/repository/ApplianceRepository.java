package ua.kiev.repairagency.repository;

import ua.kiev.repairagency.domain.appliance.ElectricApplianceEntity;

import java.util.List;

public interface ApplianceRepository {
    List<ElectricApplianceEntity> getAllAppliances();

    <T extends ElectricApplianceEntity> void save(T appliance);

    ElectricApplianceEntity findById(Long id);

    ElectricApplianceEntity deleteById(Long id);
}

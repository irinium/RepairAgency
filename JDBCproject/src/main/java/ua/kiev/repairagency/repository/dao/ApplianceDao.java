package ua.kiev.repairagency.repository.dao;

import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;

import java.util.List;
import java.util.Optional;

public interface ApplianceDao {
    List<ElectricApplianceEntity> findAll();

    <T extends ElectricApplianceEntity> void save(T appliance);

    Optional <ElectricApplianceEntity>findById(Long id);

    Optional deleteById(Long id);
}

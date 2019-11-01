package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;

import java.util.List;
import java.util.Optional;

public interface ApplianceDao extends GenericDao<ElectricApplianceEntity, Long> {
    List<ElectricApplianceEntity> findAll();

    <T extends ElectricApplianceEntity> void save(T appliance);

    Optional<ElectricApplianceEntity> findById(Long id);

    void deleteById(Long id);
}

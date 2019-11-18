package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ApplianceDao extends GenericDao<ElectricApplianceEntity, Long> {
    int save(ElectricApplianceEntity entity);

    Optional<ElectricApplianceEntity> findById(Long id);

    Optional<ElectricApplianceEntity> findByUserId(Long userId);

    List<ElectricApplianceEntity> findAll(int currentPage, int recordsPerPage);

    void update(ElectricApplianceEntity entity, String param);

    int getNumberOfRows() throws SQLException;

    int getLastInsertedId();
}

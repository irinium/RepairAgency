package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ApplianceDao extends GenericDao<ElectricApplianceEntity, Long> {
    List<ElectricApplianceEntity> findAll(int currentPage, int recordsPerPage);

    <T extends ElectricApplianceEntity> void save(T appliance);

    Optional<ElectricApplianceEntity> findById(Long id);

    List<ElectricApplianceEntity> mapResultSetToEntity(PreparedStatement statement) throws SQLException;
}

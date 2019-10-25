package ua.kiev.repairagency.repository.dao.impl;

import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ManufacturerEntity;
import ua.kiev.repairagency.entity.appliance.TypeEntity;
import ua.kiev.repairagency.repository.dao.ApplianceDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.repository.helper.SqlHelper.*;

public class ApplianceDaoImpl extends GenericDaoImpl implements ApplianceDao {
    private static final String GET_APPLIANCE_LIST_QUERY =
            "SELECT\n" +
            "    a.appliance_id a_id,\n" +
            "    a.name a_name,\n" +
            "    a.manufacturer_id am_id,\n" +
            "    a.model model,\n" +
            "    a.power_consumption power,\n" +
            "    a.disrepair disrepair,\n" +
            "    a.type_id at_id,\n" +
            "    m.manufacturer_id m_id,\n" +
            "    m.name m_name,\n" +
            "    t.type_id t_id,\n" +
            "    t.name t_name \n" +
            "FROM `Appliances` a \n" +
            "JOIN `Manufacturers` m  ON a.manufacturer_id = m.manufacturer_id \n" +
            "JOIN `Types` t ON a.type_id = t.type_id; \n";
    private static final String INSERT_REQUEST = "INSERT INTO `Appliances` " +
            "(`appliance_id`,`name`,`model`,`disrepair`,`power_consumption`,`manufacturer_id`,`type_id`)\n" +
            "VALUES (?,?,?,?,?,?,?);";
    private static final String FIND_QUERY = "SELECT * FROM `Appliances` WHERE appliance_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM `Appliances` WHERE appliance_id =?;";

    @Override
    public List<ElectricApplianceEntity> findAll() {
       return super.findAll(GET_APPLIANCE_LIST_QUERY);
    }

    @Override
    public <T extends ElectricApplianceEntity> void save(T appliance) {
        prepareStatement(INSERT_REQUEST, statement -> {
            statement.setLong(1, appliance.getId());
            statement.setString(2, appliance.getName());
            statement.setString(3, appliance.getModel());
            statement.setString(4, appliance.getDisrepair());
            statement.setInt(5, appliance.getPowerConsumption());
            statement.setInt(6, appliance.getManufacturerEntity().ordinal());
            statement.setInt(7, appliance.getTypeEntity().ordinal());
            return statement.executeUpdate();
        });
    }

    @Override
    public Optional <ElectricApplianceEntity> findById(Long id) {
        return super.findById(id, FIND_QUERY);
    }

    @Override
    public Optional <ElectricApplianceEntity> deleteById(Long id) {
        return super.deleteById(id, DELETE_QUERY);
    }

    protected Optional mapResultSetToEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ?
                Optional.ofNullable(new ElectricApplianceEntity.ElectricApplianceBuilder()
                        .withId(resultSet.getLong("a_id"))
                        .withName(resultSet.getString("a_name"))
                        .withManufacturer(ManufacturerEntity.valueOf(resultSet.getString("m_name")))
                        .withModel(resultSet.getString("model"))
                        .withPowerConsumption(resultSet.getInt("power"))
                        .withPrice(resultSet.getString("disrepair"))
                        .withType(TypeEntity.valueOf(resultSet.getString("t_name")))
                        .build()) : Optional.empty();
    }
}

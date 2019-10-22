package ua.kiev.repairagency.repository.impl;

import ua.kiev.repairagency.domain.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.domain.appliance.ManufacturerEntity;
import ua.kiev.repairagency.domain.appliance.TypeEntity;
import ua.kiev.repairagency.repository.ApplianceRepository;
import ua.kiev.repairagency.repository.helper.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ApplianceRepositoryImpl implements ApplianceRepository {
    private static final String GET_APPLIANCE_LIST_QUERY = "select\n" +
            "    a.appliance_id a_id,\n" +
            "    a.name a_name,\n" +
            "    a.manufacturer_id am_id,\n" +
            "    a.model model,\n" +
            "    a.power_consumption power,\n" +
            "    a.price price,\n" +
            "    a.type_id at_id,\n" +
            "    m.manufacturer_id m_id,\n" +
            "    m.name m_name,\n" +
            "    t.type_id t_id,\n" +
            "    t.name t_name \n" +
            "from `Appliance` a \n" +
            "join `Manufacturer` m  on a.manufacturer_id = m.manufacturer_id \n" +
            "join `Type` t on a.type_id = t.type_id \n";

    @Override
    public List<ElectricApplianceEntity> getAllAppliances() {
        List<ElectricApplianceEntity> appliances = new LinkedList<>();

        return SqlHelper.prepareStatement(GET_APPLIANCE_LIST_QUERY + ";", statement -> {
            ResultSet resultSet = statement.executeQuery();

            ElectricApplianceEntity applianceEntity = getElectricApplianceEntity(statement);
            return appliances;
        });
    }

    @Override
    public <T extends ElectricApplianceEntity> void save(T appliance) {
        String insertRequest = "insert into `Appliance` " +
                "(`appliance_id`,`name`,`model`,`price`,`power_consumption`,`manufacturer_id`,`type_id`)\n" +
                "VALUES (?,?,?,?,?,?,?);";
        SqlHelper.prepareStatement(insertRequest, preparedStatement -> {
            preparedStatement.setLong(1, appliance.getId());
            preparedStatement.setString(2, appliance.getName());
            preparedStatement.setString(3, appliance.getModel());
            preparedStatement.setDouble(4, appliance.getPrice());
            preparedStatement.setInt(5, appliance.getPowerConsumption());
            preparedStatement.setInt(6, appliance.getManufacturerEntity().ordinal());
            preparedStatement.setInt(7, appliance.getTypeEntity().ordinal());
            return preparedStatement.executeUpdate();
        });
        getAllAppliances().add(appliance);
    }

    @Override
    public ElectricApplianceEntity findById(Long id) {
        String query = GET_APPLIANCE_LIST_QUERY + " where a.appliance_id = ?;";
        return SqlHelper.prepareStatement(query, statement -> {
            statement.setLong(1, id);
            ElectricApplianceEntity applianceEntity = getElectricApplianceEntity(statement);
            return applianceEntity;
        });
    }

    private ElectricApplianceEntity getElectricApplianceEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return new ElectricApplianceEntity.ElectricApplianceBuilder()
                    .withId(resultSet.getLong("a_id"))
                    .withName(resultSet.getString("a_name"))
                    .withManufacturer(ManufacturerEntity.valueOf(resultSet.getString("m_name")))
                    .withModel(resultSet.getString("model"))
                    .withPowerConsumption(resultSet.getInt("power"))
                    .withPrice(resultSet.getInt("price"))
                    .withType(TypeEntity.valueOf(resultSet.getString("t_name")))
                    .build();
    }

    @Override
    public ElectricApplianceEntity deleteById(Long id) {
        String deleteRequest = "delete from Appliance a" +
                "where a.appliance_id =?;";
        SqlHelper.prepareStatement(deleteRequest, preparedStatement -> {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        });
        return getAllAppliances().remove(id.intValue());
    }
}

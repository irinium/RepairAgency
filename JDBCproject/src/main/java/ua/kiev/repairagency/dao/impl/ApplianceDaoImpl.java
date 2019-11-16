package ua.kiev.repairagency.dao.impl;

import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ManufacturerEntity;
import ua.kiev.repairagency.entity.appliance.TypeEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.dao.helper.SqlHelper.prepareStatement;

public class ApplianceDaoImpl extends GenericDaoImpl implements ApplianceDao {
    private final UserDao userDao;
    private static final String GET_APPLIANCE_LIST_QUERY =
            "SELECT\n" +
                    "    a.appliance_id appliance_id,\n" +
                    "    a.user_id user_id,\n" +
                    "    a.appliance_name appliance_name,\n" +
                    "    a.manufacturer_id am_id,\n" +
                    "    a.model model,\n" +
                    "    a.disrepair disrepair,\n" +
                    "    a.type_id at_id,\n" +
                    "    m.manufacturer_id m_id,\n" +
                    "    m.manufacturer_name manufacturer_name,\n" +
                    "    t.type_id t_id,\n" +
                    "    t.type_name type_name \n" +
                    " FROM `Appliances` a \n" +
                    " JOIN `Manufacturers` m  ON a.manufacturer_id = m.manufacturer_id \n" +
                    " JOIN `Types` t ON a.type_id = t.type_id; \n";
    private static final String INSERT_REQUEST = "INSERT INTO `Appliances` " +
            "(`appliance_name`,`model`,`disrepair`,`manufacturer_id`,`type_id`,`user_id`,`appliance_id`)\n" +
            "VALUES (?,?,?,?,?,?,?);";
    private static final String FIND_QUERY = "SELECT\n" +
            "    a.appliance_id appliance_id,\n" +
            "    a.user_id user_id,\n" +
            "    a.appliance_name appliance_name,\n" +
            "    a.manufacturer_id am_id,\n" +
            "    a.model model,\n" +
            "    a.disrepair disrepair,\n" +
            "    a.type_id at_id,\n" +
            "    m.manufacturer_id m_id,\n" +
            "    m.manufacturer_name manufacturer_name,\n" +
            "    t.type_id t_id,\n" +
            "    t.type_name type_name \n" +
            " FROM `Appliances` a \n" +
            " JOIN `Manufacturers` m  ON a.manufacturer_id = m.manufacturer_id \n" +
            " JOIN `Types` t ON a.type_id = t.type_id" +
            " WHERE appliance_id = ?;";
    private static final String FIND_BY_USER_ID_QUERY =  "SELECT\n" +
            "    a.appliance_id appliance_id,\n" +
            "    a.user_id user_id,\n" +
            "    a.appliance_name appliance_name,\n" +
            "    a.manufacturer_id am_id,\n" +"    a.user_id user_id,\n" +
            "    a.model model,\n" +
            "    a.disrepair disrepair,\n" +
            "    a.type_id at_id,\n" +
            "    m.manufacturer_id m_id,\n" +
            "    m.manufacturer_name manufacturer_name,\n" +
            "    t.type_id t_id,\n" +
            "    t.type_name type_name \n" +
            " FROM `Appliances` a \n" +
            " JOIN `Manufacturers` m  ON a.manufacturer_id = m.manufacturer_id \n" +
            " JOIN `Types` t ON a.type_id = t.type_id" +
            " WHERE a.user_id = ?;";

    public ApplianceDaoImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<ElectricApplianceEntity> findAll(int currentPage, int recordsPerPage) {
        return findAll(GET_APPLIANCE_LIST_QUERY,currentPage,recordsPerPage);
    }

    @Override
    public Integer save(ElectricApplianceEntity appliance) {
        return prepareStatement(INSERT_REQUEST, statement -> {
            statement.setString(1, appliance.getName());
            statement.setString(2, appliance.getModel());
            statement.setString(3, appliance.getDisrepair());
            statement.setInt(4, appliance.getManufacturerEntity().ordinal()+1);
            statement.setInt(5, appliance.getTypeEntity().ordinal()+1);
            statement.setLong(6, appliance.getUserEntity().getId());
            statement.setLong(7, appliance.getId());
            return statement.executeUpdate();
        });
    }

    @Override
    public Optional<ElectricApplianceEntity> findById(Long id) {
        return findById(id, FIND_QUERY);
    }

    @Override
    public List<ElectricApplianceEntity> findByUserId(Long id) {
        return prepareStatement(FIND_BY_USER_ID_QUERY,statement -> {
            statement.setLong(1, id);
            return Optional.ofNullable(mapResultSetToEntity(statement));
        });
    }

    public List<ElectricApplianceEntity> mapResultSetToEntity(PreparedStatement statement) throws SQLException {
        List<ElectricApplianceEntity> applianceEntityList = new LinkedList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ElectricApplianceEntity applianceEntity = ElectricApplianceEntity.builder()
                    .withId(resultSet.getLong("appliance_id"))
                    .withName(resultSet.getString("appliance_name"))
                    .withManufacturer(ManufacturerEntity.valueOf(resultSet.getString("manufacturer_name")))
                    .withModel(resultSet.getString("model"))
                    .withDisrepair(resultSet.getString("disrepair"))
                    .withType(TypeEntity.valueOf(resultSet.getString("type_name")))
                    .withUser(userDao.findById(resultSet.getLong("user_id")).get())
                    .build();
            applianceEntityList.add(applianceEntity);
        }
        return applianceEntityList;
    }
}

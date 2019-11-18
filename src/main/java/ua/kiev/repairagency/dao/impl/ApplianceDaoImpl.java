package ua.kiev.repairagency.dao.impl;

import org.apache.log4j.Logger;
import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.DataBaseConnector;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ManufacturerEntity;
import ua.kiev.repairagency.entity.appliance.TypeEntity;
import ua.kiev.repairagency.service.exception.DataBaseRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ApplianceDaoImpl extends GenericDaoImpl<ElectricApplianceEntity> implements ApplianceDao {
    private static final Logger LOGGER = Logger.getLogger(ApplianceDaoImpl.class);

    private final UserDao userDao;
    private static final String FIND_ALL_APPLIANCES_QUERY =
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

    private static final String SAVE_QUERY = "INSERT INTO `Appliances` " +
            "(`appliance_name`,`model`,`disrepair`,`manufacturer_id`,`type_id`,`user_id`)\n" +
            "VALUES (?,?,?,?,?,?);";

    private static final String FIND_BY_ID_QUERY = "SELECT\n" +
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

    private static final String FIND_BY_USER_ID_QUERY = "SELECT\n" +
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
            " WHERE a.user_id = ?;";

    private static final String UPDATE_QUERY = "UPDATE `Appliances` SET disrepair = ?;";
    private static final String NUMBER_OF_ROWS = "SELECT COUNT(appliance_id) FROM `Appliances`";
    private static final String LAST_INSERT = "SELECT LAST_INSERT_ID()";

    public ApplianceDaoImpl(DataBaseConnector connector, UserDao userDao) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_APPLIANCES_QUERY, UPDATE_QUERY, NUMBER_OF_ROWS);
        this.userDao = userDao;
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, ElectricApplianceEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getModel());
        preparedStatement.setString(3, entity.getDisrepair());
        preparedStatement.setInt(4, entity.getManufacturerEntity().ordinal() + 1);
        preparedStatement.setInt(5, entity.getTypeEntity().ordinal() + 1);
        preparedStatement.setLong(6, entity.getUserEntity().getId());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, ElectricApplianceEntity entity, String disrepair) throws SQLException {
        preparedStatement.setString(1, disrepair);
        preparedStatement.setLong(1, entity.getId());
    }


    @Override
    public Optional<ElectricApplianceEntity> findById(Long id) {
        return findByLongParam(id, FIND_BY_ID_QUERY);
    }

    public Optional<ElectricApplianceEntity> findByUserId(Long userId){
        return findByLongParam(userId, FIND_BY_USER_ID_QUERY);
    }

    @Override
    public ElectricApplianceEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ElectricApplianceEntity.builder()
                .withId(resultSet.getLong("appliance_id"))
                .withName(resultSet.getString("appliance_name"))
                .withManufacturer(ManufacturerEntity.valueOf(resultSet.getString("manufacturer_name")))
                .withModel(resultSet.getString("model"))
                .withDisrepair(resultSet.getString("disrepair"))
                .withType(TypeEntity.valueOf(resultSet.getString("type_name")))
                .withUser(userDao.findById(resultSet.getLong("user_id")).get())
                .build();
    }

    @Override
    public int save(ElectricApplianceEntity entity) {
        return super.save(entity);
    }

    @Override
    public List<ElectricApplianceEntity> findAll(int currentPage, int recordsPerPage) {
        return super.findAll(currentPage, recordsPerPage);
    }

    @Override
    public void update(ElectricApplianceEntity entity, String param) {
        super.update(entity, param);
    }

    @Override
    public int getNumberOfRows() throws SQLException {
        return super.getNumberOfRows();
    }

    public int getLastInsertedId() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LAST_INSERT)) {
            return mapResultSetToNumber(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Counting number of rows is failed", e);
            throw new DataBaseRuntimeException("Counting number of rows is failed", e);
        }
    }
}

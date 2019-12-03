package ua.kiev.repairagency.dao.impl;

import org.apache.log4j.Logger;
import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.DataBaseConnector;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.exception.DataBaseRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends GenericDaoImpl<OrderEntity> implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    private static final String FIND_ALL_ORDERS_QUERY =
            "SELECT " +
                    " o.order_id order_id," +
                    " o.price price," +
                    " o.state state," +
                    " o.status status," +
                    " u.user_id user_id," +
                    " u.user_name user_name," +
                    " u.surname user_surname," +
                    " a.appliance_id appliance_id," +
                    " mn.manufacturer_name manufacturer_name," +
                    " a.model model," +
                    " a.disrepair disrepair," +
                    " o.master_id master_id," +
                    " o.title title" +
                    " FROM `Orders` o" +
                    " JOIN `Users` u on o.user_id = u.user_id " +
                    " JOIN `Appliances` a on o.appliance_id = a.appliance_id " +
                    " JOIN `Manufacturers` mn on a.manufacturer_id = mn.manufacturer_id" +
                    " LIMIT ?,?;";
    private static final String ORDERS_BY_USER_QUERY =
            "SELECT " +
                    " o.order_id order_id," +
                    " o.price price," +
                    " o.state state," +
                    " o.status status," +
                    " u.user_id user_id," +
                    " u.user_name user_name," +
                    " u.surname user_surname," +
                    " a.appliance_id appliance_id," +
                    " mn.manufacturer_name manufacturer_name," +
                    " a.model model," +
                    " a.disrepair disrepair," +
                    " o.master_id master_id," +
                    " o.title title" +
                    " FROM `Orders` o" +
                    " JOIN `Users` u on o.user_id = u.user_id " +
                    " JOIN `Appliances` a on o.appliance_id = a.appliance_id " +
                    " JOIN `Manufacturers` mn on a.manufacturer_id = mn.manufacturer_id" +
                    " where u.user_id = ?" +
                    " LIMIT ?,?;";
    private static final String ORDERS_BY_MASTER_QUERY =
            "SELECT " +
                    " o.order_id order_id," +
                    " o.price price," +
                    " o.state state," +
                    " o.status status," +
                    " u.user_id user_id," +
                    " u.user_name user_name," +
                    " u.surname user_surname," +
                    " a.appliance_id appliance_id," +
                    " mn.manufacturer_name manufacturer_name," +
                    " a.model model," +
                    " a.disrepair disrepair," +
                    " o.master_id master_id," +
                    " o.title title" +
                    " FROM `Orders` o" +
                    " JOIN `Users` u on o.user_id = u.user_id " +
                    " JOIN `Appliances` a on o.appliance_id = a.appliance_id " +
                    " JOIN `Manufacturers` mn on a.manufacturer_id = mn.manufacturer_id" +
                    " where o.master_id = ?" +
                    " LIMIT ?,?;";
    private static final String ORDERS_WITHOUT_MASTER_QUERY =
            "SELECT " +
                    " o.order_id order_id," +
                    " o.price price," +
                    " o.state state," +
                    " o.status status," +
                    " u.user_id user_id," +
                    " u.user_name user_name," +
                    " u.surname user_surname," +
                    " a.appliance_id appliance_id," +
                    " mn.manufacturer_name manufacturer_name," +
                    " a.model model," +
                    " a.disrepair disrepair," +
                    " o.master_id master_id," +
                    " o.title title" +
                    " FROM `Orders` o" +
                    " JOIN `Users` u on o.user_id = u.user_id " +
                    " JOIN `Appliances` a on o.appliance_id = a.appliance_id " +
                    " JOIN `Manufacturers` mn on a.manufacturer_id = mn.manufacturer_id" +
                    " where o.master_id IS NULL and o.state = true" +
                    " LIMIT ?,?;";
    private static final String SAVE_QUERY = "INSERT INTO `Orders`(`title`,`user_id`,`appliance_id`,`status`) VALUES (?,?,?,?);";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Orders` WHERE order_id = ?;";
    private static final String UPDATE_BY_PRICE_QUERY = "UPDATE `Orders` SET price = ? WHERE order_id = ?;";
    private static final String UPDATE_BY_STATE_QUERY = "UPDATE `Orders` SET state = ? WHERE order_id = ?;";
    private static final String UPDATE_BY_STATUS_QUERY = "UPDATE `Orders` SET status = ? WHERE order_id = ?;";
    private static final String UPDATE_BY_MASTER_QUERY = "UPDATE `Orders` SET master_id = ? WHERE order_id = ?;";
    private static final String UPDATE_BY_TITLE_QUERY = "UPDATE `Orders` SET title = ? WHERE order_id = ?;";
    private static final String NUMBER_OF_ALL_ORDERS_ROWS = "SELECT COUNT(order_id) FROM `Orders`";
    private static final String NUMBER_OF_ORDERS_WITHOUT_MASTER_ROWS = "SELECT COUNT(order_id) FROM `Orders` " +
            "where master_id IS NULL and state = true;";
    private static final String NUMBER_OF_USER_ORDERS_ROWS = "SELECT COUNT(order_id) FROM `Orders` where user_id = ?;";
    private static final String NUMBER_OF_MASTER_ORDERS_ROWS = "SELECT COUNT(order_id) FROM `Orders` where master_id = ?;";

    private final UserDao userDao;
    private final ApplianceDao applianceDao;

    public OrderDaoImpl(UserDao userDao, ApplianceDao applianceDao, DataBaseConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_ORDERS_QUERY,
                UPDATE_BY_TITLE_QUERY, NUMBER_OF_ALL_ORDERS_ROWS);
        this.userDao = userDao;
        this.applianceDao = applianceDao;
    }

    @Override
    public void updateByMaster(OrderEntity entity, Long masterId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_MASTER_QUERY)) {
            preparedStatement.setLong(1, masterId);
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Set master to order is failed", e);
            throw new DataBaseRuntimeException("Set master to order is failed", e);
        }
    }

    @Override
    public void updateByPrice(OrderEntity entity, Double price) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_PRICE_QUERY)) {
            preparedStatement.setDouble(1, price);
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Set price to order is failed", e);
            throw new DataBaseRuntimeException("Set price to order is failed", e);
        }
    }

    @Override
    public void updateByState(OrderEntity entity, Boolean state) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_STATE_QUERY)) {
            preparedStatement.setBoolean(1, state);
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Set state to order is failed", e);
            throw new DataBaseRuntimeException("Set state to order is failed", e);
        }
    }

    @Override
    public void updateByStatus(OrderEntity entity, Boolean status) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_STATUS_QUERY)) {
            preparedStatement.setBoolean(1, status);
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Set status to order is failed", e);
            throw new DataBaseRuntimeException("Set status to order is failed", e);
        }
    }

    @Override
    public List<OrderEntity> findUserOrders(UserEntity userEntity, int currentPage, int recordsPerPage) {
        int start = currentPage * 5 - recordsPerPage;

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_BY_USER_QUERY)) {
            preparedStatement.setLong(1, userEntity.getId());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, Math.min
                    (getNumberOfRowsWithWildCard(NUMBER_OF_USER_ORDERS_ROWS, userEntity), recordsPerPage));
            return getOrderEntities(preparedStatement);

        } catch (SQLException e) {
            LOGGER.error("Selection of all users's orders is failed", e);
            throw new DataBaseRuntimeException("Selection of all user's orders is failed", e);
        }
    }

    @Override
    public List<OrderEntity> findMastersOrders(UserEntity userEntity, int currentPage, int recordsPerPage) {
        int start = currentPage * 5 - recordsPerPage;

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_BY_MASTER_QUERY)) {
            preparedStatement.setLong(1, userEntity.getId());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, Math.min
                    (getNumberOfRowsWithWildCard(NUMBER_OF_MASTER_ORDERS_ROWS, userEntity), recordsPerPage));
            return getOrderEntities(preparedStatement);

        } catch (SQLException e) {
            LOGGER.error("Selection of all orders accepted by master is failed", e);
            throw new DataBaseRuntimeException("Selection of all orders accepted by master is failed", e);
        }
    }

    private int getNumberOfRowsWithWildCard(String numberOfRowsQuery, UserEntity userEntity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(numberOfRowsQuery)) {
            preparedStatement.setLong(1, userEntity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("Counting number of rows is failed", e);
            throw new DataBaseRuntimeException("Counting number of rows is failed", e);
        }
    }

    @Override
    public int getNumberOfUserOrdersRows(UserEntity userEntity) {
        return getNumberOfRowsWithWildCard(NUMBER_OF_USER_ORDERS_ROWS, userEntity);
    }

    @Override
    public int getNumberOfMasterOrdersRows(UserEntity userEntity) {
        return getNumberOfRowsWithWildCard(NUMBER_OF_MASTER_ORDERS_ROWS, userEntity);
    }

    @Override
    public List<OrderEntity> findOrdersWithoutMaster(int currentPage, int recordsPerPage) {
        int start = currentPage * 5 - recordsPerPage;

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_WITHOUT_MASTER_QUERY)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, Math.min(numberOfOrdersWithoutMasterRows(), recordsPerPage));
            return getOrderEntities(preparedStatement);

        } catch (SQLException e) {
            LOGGER.error("Selection of all orders without master is failed", e);
            throw new DataBaseRuntimeException("Selection of all orders without master is failed", e);
        }
    }

    public int numberOfOrdersWithoutMasterRows() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NUMBER_OF_ORDERS_WITHOUT_MASTER_ROWS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("Counting number of rows is failed", e);
            throw new DataBaseRuntimeException("Counting number of rows is failed", e);
        }
    }

    private List<OrderEntity> getOrderEntities(PreparedStatement preparedStatement) throws SQLException {
        final ResultSet resultSet = preparedStatement.executeQuery();
        List<OrderEntity> entities = new ArrayList<>();
        while (resultSet.next()) {
            entities.add(mapResultSetToEntity(resultSet));
        }
        return entities;
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        return super.findByLongParam(id, FIND_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, OrderEntity entity) throws SQLException {
        try {
            preparedStatement.getConnection().setAutoCommit(false);
            applianceDao.save(entity.getApplianceEntity());
            saveToOrders(entity, preparedStatement);
            preparedStatement.getConnection().commit();
        } catch (SQLException exception) {
            preparedStatement.getConnection().rollback();
            throw new DataBaseRuntimeException(exception);
        } finally {
            preparedStatement.getConnection().setAutoCommit(true);
        }
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, OrderEntity entity, String title) throws SQLException {
        preparedStatement.setString(1, title);
        preparedStatement.setLong(2, entity.getId());
    }

    @Override
    protected OrderEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return OrderEntity.builder()
                .withId(resultSet.getLong("order_id"))
                .withPrice(resultSet.getDouble("price"))
                .withTitle(resultSet.getString("title"))
                .withState(resultSet.getBoolean("state"))
                .withApplianceEntity(applianceDao.findById(resultSet.getLong("appliance_id")).isPresent() ?
                        applianceDao.findById(resultSet.getLong("appliance_id")).get() : null)
                .withCustomerEntity(userDao.findById(resultSet.getLong("user_id")).isPresent() ?
                        userDao.findById(resultSet.getLong("user_id")).get() : null)
                .build();
    }

    private void saveToOrders(OrderEntity orderEntity, PreparedStatement statement) throws SQLException {
        statement.setString(1, orderEntity.getTitle());
        statement.setLong(2, orderEntity.getCustomerEntity().getId());
        statement.setLong(3, applianceDao.getLastInsertedId());
        statement.setBoolean(4, orderEntity.getStatus());
    }
}

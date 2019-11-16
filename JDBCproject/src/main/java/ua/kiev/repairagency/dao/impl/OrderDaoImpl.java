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

    private final UserDao userDao;
    private final ApplianceDao applianceDao;

    private static final String FIND_ALL_ORDERS_QUERY =
            "SELECT " +
                    " o.order_id order_id," +
                    " o.price price," +
                    " o.state state," +
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
                    " JOIN `Appliances` a on u.user_id = a.user_id " +
                    " JOIN `Manufacturers` mn on a.manufacturer_id = mn.manufacturer_id" +
                    " LIMIT ?,?;";

    private static final String ORDERS_BY_USER_QUERY =
            "SELECT " +
                    " o.order_id order_id," +
                    " o.price price," +
                    " o.state state," +
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
                    " JOIN `Appliances` a on u.user_id = a.user_id " +
                    " JOIN `Manufacturers` mn on a.manufacturer_id = mn.manufacturer_id" +
                    " where u.user_id = ?" +
                    " LIMIT ?,?;";

    private static final String SAVE_QUERY = "INSERT INTO `Orders`(`title`,`user_id`,`appliance_id`) VALUES (?,?,?);";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Orders WHERE order_id = ?";
    private static final String UPDATE_BY_PRICE_QUERY = "UPDATE `Orders` SET prise = ? WHERE order_id = ?";
    private static final String UPDATE_BY_STATE_QUERY = "UPDATE `Orders` SET state = ? WHERE order_id = ?";
    private static final String UPDATE_BY_MASTER_QUERY = "UPDATE `Orders` SET master_id = ? WHERE order_id = ?";
    private static final String UPDATE_BY_TITLE_QUERY = "UPDATE `Orders` SET title = ? WHERE order_id = ?";
    private static final String NUMBER_OF_ROWS = "SELECT COUNT(order_id) FROM `Orders`";

    public OrderDaoImpl(UserDao userDao, ApplianceDao applianceDao, DataBaseConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_ORDERS_QUERY, UPDATE_BY_TITLE_QUERY, NUMBER_OF_ROWS);
        this.userDao = userDao;
        this.applianceDao = applianceDao;
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, OrderEntity entity) throws SQLException {
        try {
            preparedStatement.getConnection().setAutoCommit(false);
            applianceDao.save(entity.getApplianceEntity());
            saveToOrders(entity, preparedStatement);
        } catch (SQLException | NullPointerException exception) {
            preparedStatement.getConnection().rollback();
            throw exception;
        } finally {
            preparedStatement.getConnection().setAutoCommit(true);
        }
    }

    private void saveToOrders(OrderEntity orderEntity, PreparedStatement statement) throws SQLException {
        statement.setString(1, orderEntity.getTitle());
        statement.setLong(2, orderEntity.getCustomerEntity().getId());
        statement.setLong(3, orderEntity.getApplianceEntity().getId());
        statement.executeUpdate();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, OrderEntity entity, String title) throws SQLException {
        preparedStatement.setString(1, title);
        preparedStatement.setLong(2, entity.getId());
    }

    @Override
    public void updateByMaster(OrderEntity entity, Long masterId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_MASTER_QUERY)) {
            preparedStatement.setLong(1, masterId);
            preparedStatement.setLong(2, entity.getId());
        } catch (SQLException e) {
            LOGGER.error("Set master to order is failed", e);
            throw new DataBaseRuntimeException("Set master to order is failed", e);
        }
    }

    @Override
    public void updateByPrice(OrderEntity entity, Long price) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_PRICE_QUERY)) {
            preparedStatement.setLong(1, price);
            preparedStatement.setLong(2, entity.getId());
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
        } catch (SQLException e) {
            LOGGER.error("Set master to order is failed", e);
            throw new DataBaseRuntimeException("Set master to order is failed", e);
        }
    }


    public List<OrderEntity> findUserOrders(UserEntity userEntity, int currentPage, int recordsPerPage) {
        int start = currentPage * 5 - recordsPerPage;
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERS_BY_USER_QUERY)) {
            preparedStatement.setLong(1, userEntity.getId());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, Math.min(getNumberOfRows(), recordsPerPage));

            final ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderEntity> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
            return entities;

        } catch (SQLException e) {
            LOGGER.error("Selection of all user's orders is failed", e);
            throw new DataBaseRuntimeException("Selection of all all user's orders is failed", e);
        }
    }

    @Override
    public int getNumberOfRows() throws SQLException {
        return super.getNumberOfRows();
    }

    @Override
    protected OrderEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new OrderEntity.OrderBuilder()
                .withId(resultSet.getLong("order_id"))
                .withPrice(resultSet.getLong("price"))
                .withTitle(resultSet.getString("title"))
                .withState(resultSet.getBoolean("state"))
                .withApplianceEntity(applianceDao.findById(resultSet.getLong("appliance_id")).get())
                .withCustomerEntity(userDao.findById(resultSet.getLong("user_id")).get())
                .build();
    }

    @Override
    public void save(OrderEntity entity) {
        super.save(entity);
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        return super.findByLongParam(id,FIND_BY_ID_QUERY);
    }

    @Override
    public List<OrderEntity> findAll(int currentPage, int recordsPerPage) {
        return super.findAll(currentPage, recordsPerPage);
    }

    @Override
    public void update(OrderEntity entity, String param) {
        super.update(entity, param);
    }
}

package ua.kiev.repairagency.dao.impl;

import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.dao.helper.SqlHelper.prepareStatement;

public class OrderDaoImpl extends GenericDaoImpl<OrderEntity, Long> implements OrderDao {
    private final UserDao userDao;
    private final ApplianceDao applianceDao;

    private static final String GET_ORDERS_LIST_QUERY = "SELECT " +
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
    private static final String USERS_ORDERS_LIST_QUERY = "SELECT " +
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

    private static final String INSERT_QUERY = "INSERT INTO `Orders`(`title`,`user_id`,`appliance_id`) VALUES (?,?,?);";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Orders WHERE order_id = ?";
    private static final String UPDATE_BY_PRICE_QUERY = "UPDATE `Orders` SET prise = ? WHERE order_id = ?";
    private static final String UPDATE_BY_STATE_QUERY = "UPDATE `Orders` SET state = ? WHERE order_id = ?";
    private static final String UPDATE_BY_MASTER_QUERY = "UPDATE `Orders` SET master_id = ? WHERE order_id = ?";
    private static final String NUMBER_OF_ROWS_FROM_USERS = "SELECT COUNT(order_id) FROM `Orders`";

    public OrderDaoImpl(UserDao userDao, ApplianceDao applianceDao) {
        this.userDao = userDao;
        this.applianceDao = applianceDao;
    }

    @Override
    public void save(OrderEntity orderEntity) {
        prepareStatement(INSERT_QUERY, statement -> {
            try {
                statement.getConnection().setAutoCommit(false);
                applianceDao.save(orderEntity.getApplianceEntity());
                saveToOrders(orderEntity, statement);
            } catch (SQLException | NullPointerException exception) {
                statement.getConnection().rollback();
                throw exception;
            } finally {
                statement.getConnection().setAutoCommit(true);
            }
            return statement;
        });
    }

    private void saveToOrders(OrderEntity orderEntity, PreparedStatement statement) throws SQLException {
        statement.setString(1, orderEntity.getTitle());
        statement.setLong(2, orderEntity.getCustomerEntity().getId());
        statement.setLong(3, orderEntity.getApplianceEntity().getId());
        statement.executeUpdate();
    }

    public int getNumberOfRows() throws SQLException {
        return prepareStatement(NUMBER_OF_ROWS_FROM_USERS, this::mapResultSetToNumber);
    }

    public int mapResultSetToNumber(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    public void update(OrderEntity orderEntity, Long price) {
        prepareStatement(UPDATE_BY_PRICE_QUERY, statement -> {
            statement.setLong(1, price);
            statement.setLong(2, orderEntity.getId());
            return statement.executeUpdate();
        });
    }

    public void update(OrderEntity orderEntity, Boolean state) {
        prepareStatement(UPDATE_BY_STATE_QUERY, statement -> {
            statement.setBoolean(1, state);
            statement.setLong(2, orderEntity.getId());
            return statement.executeUpdate();
        });
    }

    @Override
    public void setMaster(OrderEntity orderEntity, Long masterId) {
        prepareStatement(UPDATE_BY_MASTER_QUERY, statement -> {
            statement.setLong(1, masterId);
            statement.setLong(2, orderEntity.getId());
            return statement.executeUpdate();
        });
    }

    @Override
    public List<OrderEntity> findAll(int currentPage, int recordsPerPage) {
        return findAll(GET_ORDERS_LIST_QUERY, currentPage, recordsPerPage);
    }

    public List<OrderEntity> findUserOrders(UserEntity userEntity, int currentPage, int recordsPerPage) {
        int start = currentPage * 5 - recordsPerPage;
        return prepareStatement(USERS_ORDERS_LIST_QUERY, statement -> {
            statement.setLong(1, userEntity.getId());
            statement.setInt(2, start);
            statement.setInt(3, Math.min(getNumberOfRows(), recordsPerPage));
            return mapResultSetToEntity(statement);
        });
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        return findById(id, FIND_BY_ID_QUERY);
    }

    @Override
    protected List<OrderEntity> mapResultSetToEntity(PreparedStatement statement) throws SQLException {//TODO
        List<OrderEntity> entityList = new LinkedList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            OrderEntity orderEntity = new OrderEntity.OrderBuilder()
                    .withId(resultSet.getLong("order_id"))
                    .withPrice(resultSet.getLong("price"))
                    .withTitle(resultSet.getString("title"))
                    .withState(resultSet.getBoolean("state"))
                    .withApplianceEntity(applianceDao.findById(resultSet.getLong("appliance_id")).get())
                    .withCustomerEntity(userDao.findById(resultSet.getLong("user_id")).get())
                    .build();
            entityList.add(orderEntity);
        }
        return entityList;
    }
}

package ua.kiev.repairagency.dao.impl;

import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.entity.appliance.ElectricApplianceEntity;
import ua.kiev.repairagency.entity.appliance.ManufacturerEntity;
import ua.kiev.repairagency.entity.appliance.TypeEntity;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.entity.user.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.dao.helper.SqlHelper.*;

public class OrderDaoImpl extends GenericDaoImpl<OrderEntity, Long> implements OrderDao {
    private final UserDao userDao;
    private final ApplianceDao applianceDao;
    private static final String GET_FULL_ORDERS_LIST = "SELECT " +
            " o.order_id   order_id," +
            " o.price order_price," +
            " o.master_id master_id," +
            " o.state state," +
            " u.user_id user_id," +
            " u.email email," +
            " u.phone phone," +
            " u.password password," +
            " u.user_name user_name," +
            " u.surname user_surname," +
            " mn.manufacturer_name manufacturer_name," +
            " a.model model," +
            " a.appliance_name appliance_name," +
            " t.type_name type_name," +
            " a.disrepair disrepair," +
            " FROM `Orders` o" +
            " JOIN `Users` u on o.user_id = u.user_id " +
            " JOIN `Role` r on r.role_id = u.role_id " +
            " JOIN `Appliances` a on u.appliance_id = a.user_id " +
            " JOIN `Manufacturers` mn on a.manufacturer_id = mn.manufacturer_id" +
            " JOIN `Types` t on t.type_id = a.type_id";
    private static final String GET_ORDERS_LIST_QUERY = "SELECT " +
            " o.order_id   order_id," +
            " o.price order_price," +
            " o.state state," +
            " u.user_name user_name," +
            " u.surname user_surname," +
            " o.master_id master_id," +
            " mn.manufacturer_name manufacturer_name," +
            " a.model model," +
            " a.disrepair disrepair" +
            " o.title title" +
            " FROM `Orders` o" +
            " JOIN `Users` u on o.user_id = u.user_id " +
            " JOIN `Appliances` a on u.appliance_id = a.user_id " +
            " JOIN `Manufacturers` mn on a.manufacturer_id = mn.manufacturer_id";
    private static final String USERS_ORDERS_LIST_QUERY = "SELECT * FROM Orders where user_id = ?;";
    private static final String INSERT_QUERY = "INSERT INTO `Orders`" +
            "(`title`,`user_id`,`appliance_id`) VALUES" + " (?,?,?);";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Orders WHERE order_id = ?";
    private static final String UPDATE_BY_PRICE_QUERY = "UPDATE `Orders` SET prise = ? WHERE order_id = ?";
    private static final String UPDATE_BY_STATE_QUERY = "UPDATE `Orders` SET state = ? WHERE order_id = ?";
    private static final String UPDATE_BY_MASTER_QUERY = "UPDATE `Orders` SET master_id = ? WHERE order_id = ?";

    public OrderDaoImpl(UserDao userDao, ApplianceDao applianceDao) {
        this.userDao = userDao;
        this.applianceDao = applianceDao;
    }

    @Override
    public void save(OrderEntity orderEntity) {
        prepareStatement(INSERT_QUERY, statement -> {
            try {
                statement.getConnection().setAutoCommit(false);
                saveToOrders(orderEntity, statement);
                statement.getConnection().commit();
            }catch (SQLException exception){
                statement.getConnection().rollback();
                throw exception;
            }finally {
                statement.getConnection().setAutoCommit(true);
            }
            return statement;
        });
    }

    private void saveToOrders(OrderEntity orderEntity, PreparedStatement statement) throws SQLException {
        statement.getConnection().setAutoCommit(false);
        statement.setString(1, orderEntity.getTitle());
        statement.setLong(2, orderEntity.getCustomerEntity().getId());
        statement.setLong(3, orderEntity.getApplianceEntity().getId());
        applianceDao.save(orderEntity.getApplianceEntity());
        statement.executeUpdate();
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
        return findAll(GET_ORDERS_LIST_QUERY,currentPage,recordsPerPage);
    }

    public List<OrderEntity> findUserOrders(UserEntity userEntity) {
        return prepareStatement(USERS_ORDERS_LIST_QUERY, statement -> {
            statement.setLong(1, userEntity.getId());
            return statement.executeUpdate();
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
                    .withApplianceEntity(new ElectricApplianceEntity.ElectricApplianceBuilder()
                            .withId(resultSet.getLong("appliance_id"))
                            .withManufacturer(ManufacturerEntity.valueOf(resultSet.getString("manufacturer_name")))
                            .withModel(resultSet.getString("model"))
                            .withName(resultSet.getString("appliance_name"))
                            .withType(TypeEntity.valueOf(resultSet.getString("type_name")))
                            .withDisrepair(resultSet.getString("disrepair"))
                            .build())
                    .withCustomerEntity(new CustomerEntity.CustomerBuilder()
                            .withId(resultSet.getLong("user_id"))
                            .withName(resultSet.getString("user_name"))
                            .withSurname(resultSet.getString("surname"))
                            .withEmail(resultSet.getString("email"))
                            .withPassword(resultSet.getString("password"))
                            .withPhoneNumber(resultSet.getString("phone"))
                            .withRole(RoleEntity.valueOf(resultSet.getString("role_name")))
                            .build())
                    .withMasterEntity(new MasterEntity.MasterBuilder()
                            .withId(resultSet.getLong("user_id"))
                            .withName(resultSet.getString("user_name"))
                            .withSurname(resultSet.getString("surname"))
                            .withEmail(resultSet.getString("email"))
                            .withPassword(resultSet.getString("password"))
                            .withPhoneNumber(resultSet.getString("phone"))
                            .build())
                    .withPrice(resultSet.getLong("price"))
                    .withTitle(resultSet.getString("title"))
                    .withState(resultSet.getBoolean("state"))
                    .build();
            entityList.add(orderEntity);
        }
        return entityList;
    }
}

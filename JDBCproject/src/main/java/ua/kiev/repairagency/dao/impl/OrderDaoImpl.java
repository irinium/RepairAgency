package ua.kiev.repairagency.dao.impl;

import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.dao.helper.SqlHelper.*;

public class OrderDaoImpl extends GenericDaoImpl<OrderEntity, Long> implements OrderDao {
    private static final String GET_ORDERS_LIST_QUERY = "SELECT " +
            " o.order_id   order_id," +
            " o.price order_price," +
            " o.master_id master_id," +
            " u.user_name user_name," +
            " u.surname user_surname," +
            " mn.manufacturer_name manufacturer_name," +
            " a.model model," +
            " a.disrepair disrepair," +
            " FROM `Orders` o" +
            " JOIN `Users` u on o.user_id = u.user_id " +
            " JOIN `Appliances` a on u.appliance_id = a.user_id " +
            " JOIN `Manufacturers` mn on a.manufacturer_id = mn.manufacturer_id";
    private static final String USERS_ORDERS_LIST_QUERY = "SELECT * FROM Orders where user_id = ?;";
    private static final String INSERT_QUERY = "INSERT INTO `Orders`(`order_id`,`appliance`,`price`) VALUES" + " (?,?,?);";
    private static final String DELETE_QUERY = "DELETE FROM `Orders` WHERE order_id = ?;";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Orders WHERE order_id = ?";
    private static final String UPDATE_QUERY = "UPDATE `Orders` SET prise = ? WHERE order_id = ?";

    @Override
    public void save(OrderEntity orderEntity) {
        prepareStatement(INSERT_QUERY, statement -> {
            statement.setLong(1, orderEntity.getId());
            statement.setString(2, orderEntity.getTitle());
            statement.setLong(3, orderEntity.getPrice());
            statement.setLong(4, orderEntity.getUserId());
            statement.setLong(5, orderEntity.getApplianceId());
            statement.setLong(6, orderEntity.getMasterId());
            return statement.executeUpdate();
        });
    }

    @Override
    public void deleteById(Long id) {
       deleteById(id, DELETE_QUERY);
    }

    public void update(OrderEntity orderEntity, Long price) {
        prepareStatement(UPDATE_QUERY, statement -> {
            statement.setLong(1, price);
            statement.setLong(2, orderEntity.getId());
            return statement.executeUpdate();
        });
    }

    @Override
    public List<OrderEntity> findAll() {
        return findAll(GET_ORDERS_LIST_QUERY);
    }

    public List<OrderEntity> findUserOrders(UserEntity userEntity) {
        return prepareStatement(USERS_ORDERS_LIST_QUERY, statement -> {
            statement.setLong(1, userEntity.getId());
            return statement.executeUpdate();
        });
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
       return findById(id,FIND_BY_ID_QUERY);
    }

    protected Optional<OrderEntity> mapResultSetToEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ?
                Optional.of(new OrderEntity(
                        (resultSet.getLong("order_id")),
                        (resultSet.getLong("appliance_id")),
                        (resultSet.getLong("price")),
                        (resultSet.getLong("user_id")),
                        (resultSet.getLong("master_id")),
                        (resultSet.getString("title"))))
                : Optional.empty();
    }
}

package ua.kiev.repairagency.repository.dao.impl;

import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.repository.dao.OrderDao;
import ua.kiev.repairagency.repository.helper.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.repository.helper.SqlHelper.*;

public class OrderDaoImpl extends GenericDaoImpl<OrderEntity, Long> implements OrderDao {
    private static final String GET_ORDERS_LIST_QUERY = "SELECT * FROM `Orders`;";
    private static final String INSERT_QUERY = "INSERT INTO `Orders`(`order_id`,`title`,`price`) VALUES" + " (?,?,?);";
    private static final String DELETE_QUERY = "DELETE FROM `Orders` WHERE order_id = ?;";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Orders WHERE order_id = ?";
    private static final String UPDATE_QUERY = "UPDATE `Orders` SET prise = ? WHERE order_id = ?";

    @Override
    public void save(OrderEntity orderEntity) {
        prepareStatement(INSERT_QUERY, statement -> {
            statement.setLong(1, orderEntity.getId());
            statement.setString(2, orderEntity.getTitle());
            statement.setLong(3, orderEntity.getPrice());
            return statement.executeUpdate();
        });
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id, DELETE_QUERY);
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
        return super.findAll(GET_ORDERS_LIST_QUERY);
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
       return super.findById(id,FIND_BY_ID_QUERY);
    }

    protected Optional mapResultSetToEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ?
                Optional.of(new OrderEntity(
                        (resultSet.getLong("order_id")),
                        (resultSet.getString("title"))))
                : Optional.empty();
    }
}

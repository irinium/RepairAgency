package ua.kiev.repairagency.repository.impl;

import ua.kiev.repairagency.domain.order.OrderEntity;
import ua.kiev.repairagency.repository.OrderRepository;
import ua.kiev.repairagency.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private final String GET_ORDERS_LIST_QUERY =
            "select\n" +
                    "    o.order_id order_id,\n" +
                    "    o.title title,\n" +
                    "    o.price price\n" +
                    " from `Order` o;\n";
    private List<OrderEntity> orderEntities = new ArrayList<>();

    public void save(OrderEntity orderEntity) {
        String insertRequest = "insert into `Order`" +
                "(`order_id`,`title`,`price`)\n" +
                "VALUES" + " (?,?,?);";

        SqlHelper.prepareStatement(insertRequest, preparedStatement -> {
            preparedStatement.setLong(1, orderEntity.getId());
            preparedStatement.setString(2, orderEntity.getTitle());
            preparedStatement.setDouble(3, orderEntity.getPrice());
            return preparedStatement.executeUpdate();
        });
        orderEntities.add(orderEntity);
    }

    public void delete(OrderEntity orderEntity) {
        String deleteRequest = "delete from `Order`" +
                "where order_id = ?;";
        SqlHelper.prepareStatement(deleteRequest, preparedStatement -> {
            preparedStatement.setLong(1, orderEntity.getId());
            return preparedStatement.executeUpdate();
        });
        orderEntities.remove(orderEntity);
    }

    public List<OrderEntity> getAll() {
        return SqlHelper.prepareStatement(GET_ORDERS_LIST_QUERY, statement -> {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderEntity order = new OrderEntity(resultSet.getLong("order_id"),
                        resultSet.getString("title"),
                        resultSet.getDouble("price"));
                orderEntities.add(order);
            }
            return orderEntities;
        });
    }
}

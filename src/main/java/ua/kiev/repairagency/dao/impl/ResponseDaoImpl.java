package ua.kiev.repairagency.dao.impl;

import ua.kiev.repairagency.dao.DataBaseConnector;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.entity.order.ResponseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseDaoImpl extends GenericDaoImpl<ResponseEntity> {
    private final UserDao userDao;

    private static final String FIND_ALL_RESPONSES_QUERY = "SELECT * FROM `Responses`;";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Responses` WHERE response_id = ?;";
    private static final String SAVE_QUERY = "INSERT INTO `Responses` (`text`,`user_id`) VALUES(?,?);";
    private static final String UPDATE_RESPONSE_QUERY = "UPDATE `Responses` SET text = ? WHERE response_id = ?;";
    private static final String NUMBER_OF_ROWS = "SELECT COUNT(order_id) FROM `Orders`";


    public ResponseDaoImpl(UserDao userDao, DataBaseConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_RESPONSES_QUERY, UPDATE_RESPONSE_QUERY, NUMBER_OF_ROWS);
        this.userDao = userDao;
    }

    @Override
    protected ResponseEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new ResponseEntity(
                resultSet.getString("text"),
                userDao.findById(resultSet.getLong("user_id")).get());
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, ResponseEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getText());
        preparedStatement.setLong(2, entity.getUser().getId());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, ResponseEntity entity, String text) throws SQLException {
        preparedStatement.setString(1, text);
        preparedStatement.setLong(2, entity.getId());
    }
}

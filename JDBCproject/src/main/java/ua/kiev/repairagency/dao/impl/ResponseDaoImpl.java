package ua.kiev.repairagency.dao.impl;

import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.entity.order.ResponseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.dao.helper.SqlHelper.prepareStatement;

public class ResponseDaoImpl extends GenericDaoImpl {
    private static final String GET_RESPONSES_QUERY = "SELECT * FROM `Responses`;";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Responses` WHERE response_id = ?;";
    private static final String INSERT_QUERY = "INSERT INTO `Responses` (`text`,`user_id`) VALUES(?,?);";
    private final UserDao userDao;

    public ResponseDaoImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected List<ResponseEntity> mapResultSetToEntity(PreparedStatement statement) throws SQLException {
        List<ResponseEntity> responseEntityList = new LinkedList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ResponseEntity responseEntity = new ResponseEntity(
                    resultSet.getString("text"),
                    userDao.findById(resultSet.getLong("user_id")).get());
            responseEntityList.add(responseEntity);
        }
        return responseEntityList;
    }

    public List findAll(int currentPage, int recordsPerPage) {
        return super.findAll(GET_RESPONSES_QUERY,currentPage,recordsPerPage);
    }

    public Optional findById(Long id) {
        return super.findById(id, FIND_BY_ID_QUERY);
    }

    public Integer save(ResponseEntity responseEntity) {
       return prepareStatement(INSERT_QUERY, statement -> {
            statement.setString(1, responseEntity.getText());
            statement.setLong(2, responseEntity.getUser().getId());
            return statement.executeUpdate();
        });
    }
}

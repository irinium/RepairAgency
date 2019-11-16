package ua.kiev.repairagency.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.dao.helper.SqlHelper.prepareStatement;

public abstract class GenericDaoImpl<E, ID> {
    private static final String NUMBER_OF_ROWS_FROM_USERS = "SELECT COUNT(user_id) FROM `Users`";


    public List<E> findAll(String query, int currentPage, int recordsPerPage) {
        int start = currentPage * 5 - recordsPerPage;

        return prepareStatement(query, statement -> {
            statement.setInt(1, start);
            statement.setInt(2, Math.min(getNumberOfRows(), recordsPerPage));
            return mapResultSetToEntity(statement);
        });
    }


    public int getNumberOfRows() throws SQLException {
        return prepareStatement(NUMBER_OF_ROWS_FROM_USERS, this::mapResultSetToNumber);
    }

    public int mapResultSetToNumber(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    public Optional<E> findById(Long id, String query) {
        return prepareStatement(query, statement -> {
            statement.setLong(1, id);
            return Optional.ofNullable(mapResultSetToEntity(statement).size() == 0 ?
                    null : mapResultSetToEntity(statement).get(0));
        });
    }

    protected abstract List<E> mapResultSetToEntity(PreparedStatement statement) throws SQLException;
}

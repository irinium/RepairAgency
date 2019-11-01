package ua.kiev.repairagency.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.dao.helper.SqlHelper.prepareStatement;

public abstract class GenericDaoImpl<E, ID> {

    public List<E> findAll(String query) {
        List<Optional> list = new LinkedList<>();
        return prepareStatement(query, statement ->
                list.add(mapResultSetToEntity(statement)));
    }

    public Optional<E> findById(Long id, String query) {
        return prepareStatement(query, statement -> {
            statement.setLong(1, id);
            return mapResultSetToEntity(statement);
        });
    }

    public Optional<E> deleteById(Long id, String query) {
        return prepareStatement(query, statement -> {
            statement.setLong(1, id);
            return statement.executeUpdate();
        });
    }

    protected abstract Optional<E> mapResultSetToEntity(PreparedStatement statement) throws SQLException;
}

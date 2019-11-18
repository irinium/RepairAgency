package ua.kiev.repairagency.dao.impl;

import org.apache.log4j.Logger;
import ua.kiev.repairagency.dao.DataBaseConnector;
import ua.kiev.repairagency.dao.GenericDao;
import ua.kiev.repairagency.service.exception.DataBaseRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;


public abstract class GenericDaoImpl<E> implements GenericDao<E, Long> {
    private static final Logger LOGGER = Logger.getLogger(GenericDaoImpl.class);

    private static final BiConsumer<PreparedStatement, String> STING_CONSUMER
            = (PreparedStatement preparedStatement, String param) -> {
        try {
            preparedStatement.setString(1, param);
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    };

    private static final BiConsumer<PreparedStatement, Long> LONG_CONSUMER
            = (PreparedStatement preparedStatement, Long param) -> {
        try {
            preparedStatement.setLong(1, param);
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    };

    protected final DataBaseConnector connector;
    private final String saveQuery;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String updateQuery;
    private final String numberOfRows;

    public GenericDaoImpl(DataBaseConnector connector, String saveQuery, String findByIdQuery,
                          String findAllQuery, String updateQuery, String numberOfRows) {
        this.connector = connector;
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.updateQuery = updateQuery;
        this.numberOfRows = numberOfRows;
    }


    @Override
    public int save(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {
            insert(preparedStatement, entity);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    }

    @Override
    public Optional<E> findById(Long id) {
        return findByLongParam(id, findByIdQuery);
    }

    @Override
    public List<E> findAll(int currentPage, int recordsPerPage) {
        int start = currentPage * 5 - recordsPerPage;

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {

            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, Math.min(getNumberOfRows(), recordsPerPage));

            final ResultSet resultSet = preparedStatement.executeQuery();
            List<E> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
            return entities;

        } catch (SQLException e) {
            LOGGER.error("Selection of all entities is failed", e);
            throw new DataBaseRuntimeException("Selection of all entities is failed", e);
        }
    }

    @Override
    public void update(E entity, String param) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            updateValues(preparedStatement, entity, param);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Update is failed", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    protected Optional<E> findByLongParam(Long id, String query) {
        return findByParam(id, query, LONG_CONSUMER);
    }

    protected Optional<E> findByStringParam(String param, String query) {
        return findByParam(param, query, STING_CONSUMER);
    }

    private <P> Optional<E> findByParam(P param, String query, BiConsumer<PreparedStatement, P> consumer) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            consumer.accept(preparedStatement, param);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Optional.ofNullable(mapResultSetToEntity(resultSet)) : Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error("Finding failed");
            throw new DataBaseRuntimeException(e);
        }
    }

    public int getNumberOfRows() throws SQLException {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(numberOfRows)) {
            return mapResultSetToNumber(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Counting number of rows is failed", e);
            throw new DataBaseRuntimeException("Counting number of rows is failed", e);
        }
    }

    public int mapResultSetToNumber(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract void insert(PreparedStatement preparedStatement, E entity) throws SQLException;

    protected abstract void updateValues(PreparedStatement preparedStatement, E entity, String password) throws SQLException;
}

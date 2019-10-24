package ua.kiev.repairagency.repository.dao.impl;

import ua.kiev.repairagency.entity.user.RoleEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.repository.dao.UserDao;
import ua.kiev.repairagency.repository.helper.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String GET_USERS_LIST_QUERY =
            "SELECT\n" +
                    "    u.user_id user_id,\n" +
                    "    u.email email,\n" +
                    "    u.password password,\n" +
                    "    u.role_id role_id,\n" +
                    "    r.role_id r_id,\n" +
                    "    r.name role_name\n" +
                    "FROM `Users` u \n" +
                    "JOIN `Roles` r ON u.role_id = r.role_id;";
    private static final String INSERT_QUERY = "INSERT INTO `Users`" +
            "(`user_id`,`email`,`password`,`role_id`)\n" +
            "VALUES (?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Users` WHERE user_id = ?;";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM `Users` WHERE email = ?;";
    private static final String DELETE_QUERY = "DELETE FROM `Users` WHERE user_id = ?;";
    private static final String UPDATE_QUERY = "UPDATE `Users` SET password = ? WHERE user_id = ?;";

    @Override
    public List<UserEntity> findAll() {
        List<Optional> userEntities = new LinkedList<>();
        return SqlHelper.prepareStatement(GET_USERS_LIST_QUERY, statement ->
                userEntities.add(mapResultSetToEntity(statement)));
    }

    @Override
    public <T extends UserEntity> T save(T user) {
        return SqlHelper.prepareStatement(INSERT_QUERY, preparedStatement -> {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getRoleEntity().ordinal());
            return preparedStatement.executeUpdate();
        });
    }

    @Override
    public Optional findById(Long id) {
        return SqlHelper.prepareStatement(FIND_BY_ID_QUERY, statement -> {
            statement.setLong(1, id);
            return mapResultSetToEntity(statement);
        });
    }

    public Optional<UserEntity> findByEmail(String email) {
        return SqlHelper.prepareStatement(FIND_BY_EMAIL_QUERY, statement -> {
            statement.setString(1, email);
            return mapResultSetToEntity(statement);
        });
    }

    @Override
    public void update(UserEntity userEntity, String password) {
        SqlHelper.prepareStatement(UPDATE_QUERY, preparedStatement -> {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, userEntity.getId());
            return preparedStatement.executeUpdate();
        });
    }

    @Override
    public UserEntity deleteById(Long id) {
        return SqlHelper.prepareStatement(DELETE_QUERY, preparedStatement -> {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        });
    }

    private Optional mapResultSetToEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() ?
                Optional.ofNullable(new UserEntity.UserBuilder()
                        .withId(resultSet.getLong("user_id"))
                        .withEmail(resultSet.getString("email"))
                        .withPassword(resultSet.getString("password"))
                        .withRole(RoleEntity.valueOf(resultSet.getString("role_name")))
                        .build()) : Optional.empty();
    }
}

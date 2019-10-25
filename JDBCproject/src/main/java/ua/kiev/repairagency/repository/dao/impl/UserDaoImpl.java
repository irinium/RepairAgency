package ua.kiev.repairagency.repository.dao.impl;

import ua.kiev.repairagency.entity.user.RoleEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.repository.dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.repository.helper.SqlHelper.*;

public class UserDaoImpl extends GenericDaoImpl<UserEntity, Long> implements UserDao {
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
            "(`user_id`,`email`,`password`,`role_id`) VALUES (?,?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Users` WHERE user_id = ?;";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM `Users` WHERE email = ?;";
    private static final String DELETE_QUERY = "DELETE FROM `Users` WHERE user_id = ?;";
    private static final String UPDATE_QUERY = "UPDATE `Users` SET password = ? WHERE user_id = ?;";

    @Override
    public List<UserEntity> findAll() {
      return super.findAll(GET_USERS_LIST_QUERY);
    }

    @Override
    public <T extends UserEntity> T save(T user) {
        return prepareStatement(INSERT_QUERY, statement -> {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRoleEntity().ordinal());
            return statement.executeUpdate();
        });
    }

    @Override
    public Optional findById(Long id) {
        return super.findById(id, FIND_BY_ID_QUERY);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return prepareStatement(FIND_BY_EMAIL_QUERY, statement -> {
            statement.setString(1, email);
            return mapResultSetToEntity(statement);
        });
    }

    @Override
    public void update(UserEntity userEntity, String password) {
        prepareStatement(UPDATE_QUERY, statement -> {
            statement.setString(1, password);
            statement.setLong(2, userEntity.getId());
            return statement.executeUpdate();
        });
    }

    @Override
    public Optional<UserEntity> deleteById(Long id) {
     return super.deleteById(id,DELETE_QUERY);
    }

    protected Optional mapResultSetToEntity(PreparedStatement statement) throws SQLException {
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

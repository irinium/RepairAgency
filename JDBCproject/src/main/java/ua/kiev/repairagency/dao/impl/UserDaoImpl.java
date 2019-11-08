package ua.kiev.repairagency.dao.impl;

import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.entity.user.RoleEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static ua.kiev.repairagency.dao.helper.SqlHelper.prepareStatement;

public class UserDaoImpl extends GenericDaoImpl<UserEntity, Long> implements UserDao {
    private static final String GET_USERS_LIST_QUERY =
            "SELECT\n" +
                    "    u.user_id user_id,\n" +
                    "    u.email email,\n" +
                    "    u.password password,\n" +
                    "    u.user_name user_name,\n" +
                    "    u.surname surname,\n" +
                    "    u.phone phone,\n" +
                    "    r.role_name role_name\n" +
                    " FROM `Users` u \n" +
                    " LEFT JOIN `Roles` r ON u.role_id = r.role_id" +
                    " GROUP BY u.user_id " +
                    " LIMIT ?,?;";

    private static final String INSERT_QUERY = "INSERT INTO `Users`" +
            "(`email`,`password`,`phone`,`role_id`, `user_name`,`surname`) VALUES (?,?,?,?,?,?);";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM `Users` WHERE user_id = ?;";

    private static final String FIND_BY_EMAIL_QUERY = "SELECT\n" +
            "    u.user_id user_id,\n" +
            "    u.email email,\n" +
            "    u.password password,\n" +
            "    u.user_name user_name,\n" +
            "    u.surname surname,\n" +
            "    u.phone phone,\n" +
            "    r.role_name role_name\n" +
            " FROM `Users` u \n" +
            " JOIN `Roles` r ON u.role_id = r.role_id WHERE email = ?;";

    private static final String UPDATE_QUERY = "UPDATE `Users` SET password = ? WHERE user_id = ?;";

    @Override
    public List<UserEntity> findAll(int currentPage, int recordsPerPage) {
        return findAll(GET_USERS_LIST_QUERY, currentPage, recordsPerPage);
    }

    public int getNumberOfRows() throws SQLException {
        return super.getNumberOfRows();
    }

    @Override
    public Integer save(UserEntity user) {
        return prepareStatement(INSERT_QUERY, statement -> {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(5, user.getName());
            statement.setString(6, user.getSurname());
            statement.setInt(4, user.getRoleEntity().ordinal()+1);
            return statement.executeUpdate();
        });
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return findById(id, FIND_BY_ID_QUERY);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return prepareStatement(FIND_BY_EMAIL_QUERY, statement -> {
            statement.setString(1, email);
            return Optional.ofNullable(mapResultSetToEntity(statement).isEmpty()? null: mapResultSetToEntity(statement).get(0));
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
    public List<UserEntity> mapResultSetToEntity(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        List<UserEntity> list = new LinkedList<>();
        while (resultSet.next()) {
            UserEntity userEntity = new UserEntity.UserBuilder()
                    .withId(resultSet.getLong("user_id"))
                    .withEmail(resultSet.getString("email"))
                    .withPassword(resultSet.getString("password"))
                    .withPhoneNumber(resultSet.getString("phone"))
                    .withName(resultSet.getString("user_name"))
                    .withSurname(resultSet.getString("surname"))
                    .withRole(RoleEntity.valueOf(resultSet.getString("role_name")))
                    .build();
            list.add(userEntity);
        }
        return list;
    }
}

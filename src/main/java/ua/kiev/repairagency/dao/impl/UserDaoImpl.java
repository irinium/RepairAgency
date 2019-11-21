package ua.kiev.repairagency.dao.impl;

import ua.kiev.repairagency.dao.DataBaseConnector;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.entity.user.RoleEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UserDaoImpl extends GenericDaoImpl<UserEntity> implements UserDao {
    private static final String FIND_ALL_QUERY =
            "SELECT " +
                    "    u.user_id user_id," +
                    "    u.email email," +
                    "    u.password password," +
                    "    u.user_name user_name," +
                    "    u.surname surname," +
                    "    u.phone phone," +
                    "    r.role_name role_name" +
                    " FROM `Users` u " +
                    " LEFT JOIN `Roles` r ON u.role_id = r.role_id" +
                    " WHERE u.role_id = 1 " +
                    " GROUP BY u.user_id " +
                    " LIMIT ?,?;";
    private static final String SAVE_QUERY = "INSERT INTO `Users`" +
            "(`email`,`password`,`phone`,`role_id`, `user_name`,`surname`) VALUES (?,?,?,?,?,?);";
    private static final String FIND_BY_ID_QUERY = "SELECT\n" +
            "    u.user_id user_id,\n" +
            "    u.email email,\n" +
            "    u.password password,\n" +
            "    u.user_name user_name,\n" +
            "    u.surname surname,\n" +
            "    u.phone phone,\n" +
            "    r.role_name role_name\n" +
            " FROM `Users` u \n" +
            " LEFT JOIN `Roles` r ON u.role_id = r.role_id" +
            " WHERE u.user_id = ?;";
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
    private static final String NUMBER_OF_ROWS = "SELECT COUNT(user_id) FROM `Users`";

    public UserDaoImpl(DataBaseConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, NUMBER_OF_ROWS);
    }

    @Override
    public void insert(PreparedStatement preparedStatement, UserEntity user) throws SQLException {
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getPhoneNumber());
        preparedStatement.setString(5, user.getName());
        preparedStatement.setString(6, user.getSurname());
        preparedStatement.setInt(4, user.getRoleEntity().ordinal() + 1);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return findByLongParam(id, FIND_BY_ID_QUERY);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return findByStringParam(email, FIND_BY_EMAIL_QUERY);
    }

    @Override
    public UserEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .withId(resultSet.getLong("user_id"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withPhoneNumber(resultSet.getString("phone"))
                .withName(resultSet.getString("user_name"))
                .withSurname(resultSet.getString("surname"))
                .withRole(RoleEntity.valueOf(resultSet.getString("role_name")))
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, UserEntity entity, String password) throws SQLException {
        preparedStatement.setString(1, password);
        preparedStatement.setLong(2, entity.getId());
    }
}

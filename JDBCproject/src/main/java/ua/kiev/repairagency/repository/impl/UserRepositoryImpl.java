package ua.kiev.repairagency.repository.impl;

import ua.kiev.repairagency.domain.user.RoleEntity;
import ua.kiev.repairagency.domain.user.UserEntity;
import ua.kiev.repairagency.repository.UserRepository;
import ua.kiev.repairagency.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final String getUsersListQuery =
            "select\n" +
                    "    u.user_id user_id,\n" +
                    "    u.email email,\n" +
                    "    u.password password,\n" +
                    "    u.role_id role_id,\n" +
                    "    r.role_id r_id,\n" +
                    "    r.name role_name\n" +
                    "from User u \n" +
                    "join Role r on u.role_id = r.role_id\n";

    @Override
    public List<UserEntity> getUserEntities() {
        List<UserEntity> userEntities = new LinkedList<>();
        return SqlHelper.prepareStatement(getUsersListQuery + ";", statement -> {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity.UserBuilder()
                        .withId(resultSet.getLong("user_id"))
                        .withEmail(resultSet.getString("email"))
                        .withPassword(resultSet.getString("password"))
                        .withRole(RoleEntity.valueOf(resultSet.getString("role_name")))
                        .build();
                userEntities.add(userEntity);
            }
            return userEntities;
        });
    }

    @Override
    public <T extends UserEntity> void save(T user) {
        String insertRequest = "insert into `User`" +
                "(`user_id`,`email`,`password`,`role_id`)\n" +
                "VALUES (?,?,?,?)";

        SqlHelper.prepareStatement(insertRequest, preparedStatement -> {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getRoleEntity().ordinal());
            return preparedStatement.executeUpdate();
        });
        getUserEntities().add(user);
    }

    @Override
    public UserEntity findById(Long id) {
        String query = getUsersListQuery + " where u.user_id = ?;";
        return SqlHelper.prepareStatement(query, statement -> {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            UserEntity userEntity = new UserEntity.UserBuilder()
                        .withId(resultSet.getLong("user_id"))
                        .withEmail(resultSet.getString("email"))
                        .withPassword(resultSet.getString("password"))
                        .withRole(RoleEntity.valueOf(resultSet.getString("role_name")))
                        .build();
            return userEntity;
        });
    }

    public UserEntity findByEmail(String email) {
        String selectQuery = "select * from User u \n" +
                " where u.email = ?;";
        return SqlHelper.prepareStatement(selectQuery, statement -> {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            UserEntity userEntity = new UserEntity.UserBuilder().build();
            while (resultSet.next()) {
                userEntity = new UserEntity.UserBuilder()
                        .withId(resultSet.getLong("user_id"))
                        .withEmail(resultSet.getString("email"))
                        .withPassword(resultSet.getString("password"))
                        .build();
            }
            return userEntity;
        });
    }

    @Override
    public void update(UserEntity userEntity, String password) {
        String updateRequest = "update User u set u.password = ? where u.user_id = ?;" ;
        SqlHelper.prepareStatement(updateRequest, preparedStatement -> {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, userEntity.getId());
            return preparedStatement.executeUpdate();
        });
    }

    @Override
    public UserEntity deleteById(Long id) {
        String deleteRequest = "delete from User u" +
                "where u.user_id = ?;";
        SqlHelper.prepareStatement(deleteRequest, preparedStatement -> {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        });
        return getUserEntities().remove(id.intValue());
    }
}

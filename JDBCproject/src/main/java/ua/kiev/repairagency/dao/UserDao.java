package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.user.UserEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<UserEntity, Long> {
    void save(UserEntity entity);

    List<UserEntity> findAll(int currentPage, int recordsPerPage);

    Optional<UserEntity> findByEmail(String email);

    int getNumberOfRows() throws SQLException;

    void update(UserEntity entity, String param);
}

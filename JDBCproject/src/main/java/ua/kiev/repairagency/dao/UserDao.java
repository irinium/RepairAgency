package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.user.UserEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<UserEntity, Long> {

    Integer save(UserEntity user);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAll(int currentPage, int recordsPerPage);

    void update(UserEntity userEntity, String password);

    List<UserEntity> mapResultSetToEntity(PreparedStatement statement) throws SQLException;

    int getNumberOfRows() throws SQLException;
}

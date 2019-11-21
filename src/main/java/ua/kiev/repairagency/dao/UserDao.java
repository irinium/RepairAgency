package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.Optional;

public interface UserDao extends GenericDao<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    int getNumberOfRows();

    void update(UserEntity entity, String param);
}

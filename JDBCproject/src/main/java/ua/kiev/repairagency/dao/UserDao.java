package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<UserEntity, Long> {

    <T extends UserEntity> T save(T user);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAll();

    void update(UserEntity userEntity, String password);

    void deleteById(Long id);
}

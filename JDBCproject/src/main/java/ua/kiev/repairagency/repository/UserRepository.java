package ua.kiev.repairagency.repository;

import ua.kiev.repairagency.domain.user.UserEntity;

import java.util.List;

public interface UserRepository {
    List<UserEntity> getUserEntities();

    <T extends UserEntity> void save(T user);

    UserEntity findById(Long id);

    UserEntity findByEmail(String email);

    void update(UserEntity userEntity, String password);

    UserEntity deleteById(Long id);
}

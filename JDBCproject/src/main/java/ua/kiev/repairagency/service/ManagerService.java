package ua.kiev.repairagency.service;

import ua.kiev.repairagency.entity.user.ManagerEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.Optional;

public interface ManagerService {
    void register(ManagerEntity managerEntity);

    UserEntity login(String email, String password);

    Optional findById(Long id) throws NoSuchFieldException;

    void update(UserEntity userEntity, String password);

    UserEntity deleteById(Long id) throws NoSuchFieldException;

    Optional findByEmail(String email);

    void sendAdvertisements (ManagerEntity managerEntity,String advertisement);
}

package ua.kiev.repairagency.service;

import ua.kiev.repairagency.domain.user.ManagerEntity;
import ua.kiev.repairagency.domain.user.UserEntity;

public interface ManagerService {
    void register(ManagerEntity managerEntity);

    UserEntity login(String email, String password);

    UserEntity findById(Long id) throws NoSuchFieldException;

    void update(UserEntity userEntity, String password);

    UserEntity deleteById(Long id) throws NoSuchFieldException;

    UserEntity findByEmail(String email);

    void sendAdvertisements (ManagerEntity managerEntity,String advertisement);
}

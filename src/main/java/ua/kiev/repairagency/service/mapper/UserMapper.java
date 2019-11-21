package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.RoleEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import java.util.Optional;

public class UserMapper {

    public User mapUserEntityToUser(UserEntity userEntity) {
        if (userEntity == null){
            return null;
        }
        return User.builder()
                .withId(userEntity.getId())
                .withEmail(userEntity.getEmail())
                .withName(userEntity.getName())
                .withSurname(userEntity.getSurname())
                .withPassword(userEntity.getPassword())
                .withPhoneNumber(userEntity.getPhoneNumber())
                .withRole(Optional.of(Role.valueOf(userEntity.getRoleEntity().name())).orElse(null))
                .build();
    }

    public UserEntity mapUserToUserEntity(User user) {
        return UserEntity.builder()
                .withId(user.getId())
                .withEmail(user.getEmail())
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withPassword(user.getPassword())
                .withPhoneNumber(user.getPhone())
                .withRole(Optional.of(RoleEntity.valueOf(user.getRole().name())).orElse(null))
                .build();
    }
}

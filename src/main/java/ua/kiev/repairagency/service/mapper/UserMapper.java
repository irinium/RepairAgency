package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.RoleEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

public class UserMapper {

    public User mapUserEntityToUser(UserEntity userEntity) {
        return User.builder()
                .withId(userEntity.getId())
                .withEmail(userEntity.getEmail())
                .withName(userEntity.getName())
                .withSurname(userEntity.getSurname())
                .withPassword(userEntity.getPassword())
                .withPhoneNumber(userEntity.getPhoneNumber())
                .withRole(Role.valueOf(userEntity.getRoleEntity().name()))
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
                .withRole(RoleEntity.valueOf(user.getRole().name()))
                .build();
    }
}

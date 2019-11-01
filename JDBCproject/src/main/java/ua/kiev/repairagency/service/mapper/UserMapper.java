package ua.kiev.repairagency.service.mapper;

import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.RoleEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.PasswordEncoder;

public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapUserEntityToUser(UserEntity userEntity) {
        return new User.UserBuilder()
                .withId(userEntity.getId())
                .withEmail(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .withPhoneNumber(userEntity.getPhoneNumber())
                .withRole(Role.valueOf(userEntity.getRoleEntity().name()))
                .build();
    }

    public UserEntity mapUserToUserEntity(User user) {
        return new UserEntity.UserBuilder()
                .withId(user.getId())
                .withEmail(user.getEmail())
                .withPassword(passwordEncoder.encode(user.getPassword()))
                .withRole(RoleEntity.valueOf(user.getRole().name()))
                .build();
    }
}

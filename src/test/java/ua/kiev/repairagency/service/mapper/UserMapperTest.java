package ua.kiev.repairagency.service.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.RoleEntity;
import ua.kiev.repairagency.entity.user.UserEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {
    private static final String PASSWORD = "password";

    private static final Long ID = 1L;

    private static final String NAME = "Name";

    private static final String SURNAME = "Surname";

    private static final String EMAIL = "email@gmail.com";

    @InjectMocks
    private UserMapper userMapper;

    @Test
    public void mapUserEntityToUserShouldReturnUser() {
        final UserEntity userEntity = UserEntity.builder()
                .withId(ID)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withPassword(PASSWORD)
                .withRole(RoleEntity.CUSTOMER)
                .build();

        final User user = userMapper.mapUserEntityToUser(userEntity);
        assertThat("mapping id is failed", user.getId(), is(ID));
        assertThat("mapping name is failed", user.getName(), is(NAME));
        assertThat("mapping surname is failed", user.getSurname(), is(SURNAME));
        assertThat("mapping email is failed", user.getEmail(), is(EMAIL));
        assertThat("mapping password is failed", user.getPassword(), is(PASSWORD));
        assertThat("mapping roles is failed", user.getRole(), is(Role.CUSTOMER));
    }

    @Test
    public void mapUserToUserEntityShouldReturnUserEntity(){
        final User user = User.builder()
                .withId(ID)
                .withName(NAME)
                .withSurname(SURNAME)
                .withEmail(EMAIL)
                .withPassword(PASSWORD)
                .withRole(Role.CUSTOMER)
                .build();

        final UserEntity userEntity = userMapper.mapUserToUserEntity(user);
        assertThat("mapping name is failed", userEntity.getName(), is(NAME));
        assertThat("mapping surname is failed", userEntity.getSurname(), is(SURNAME));
        assertThat("mapping email is failed", userEntity.getEmail(), is(EMAIL));
        assertThat("mapping password is failed", userEntity.getPassword(), is(PASSWORD));
        assertThat("mapping roles is failed", userEntity.getRoleEntity(), is(RoleEntity.CUSTOMER));
    }
}
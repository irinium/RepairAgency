package ua.kiev.repairagency.service.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.encoder.PasswordEncoder;
import ua.kiev.repairagency.service.exception.AlreadyRegisteredException;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.UserValidator;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserGenericServiceImplTest {
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final User USER = User.builder().withId(1L).withEmail(EMAIL).build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().withEmail(EMAIL).build();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private UserGenericServiceImpl userGenericServiceImpl;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserDao userDao;
    @Mock
    private UserValidator userValidator;
    @Mock
    private UserMapper userMapper;

    @Test
    public void registrationShouldBeSuccessful() {
        doNothing().when(userValidator).validate(USER);
        when(userDao.findByEmail(EMAIL)).thenReturn(Optional.empty());
        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);
        when(passwordEncoder.encode(USER.getPassword())).thenReturn(PASSWORD);

        userGenericServiceImpl.register(USER);
        verify(userDao).save(USER_ENTITY);
    }

    @Test
    public void registrationShouldThrowExceptionWhenUserAlreadyRegistered() {
        doNothing().when(userValidator).validate(USER);
        when(userDao.findByEmail(EMAIL)).thenReturn(Optional.of(USER_ENTITY));
        exception.expect(AlreadyRegisteredException.class);
        userGenericServiceImpl.register(USER);
    }

    @Test
    public void loginShouldReturnNullWhenEmailIsAbsentInDatabase() {
        when(userDao.findByEmail("admin@gmail.com")).thenReturn(Optional.empty());
        User actual = userGenericServiceImpl.login("admin@gmail.com", "password");
        User expected = null;

        assertThat("Login is failed", actual, is(expected));
    }

    @Test
    public void updateShouldCallUpdateMethodInDao() {
        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);

        userGenericServiceImpl.updatePassword(USER, PASSWORD);
        verify(userDao).update(USER_ENTITY, PASSWORD);
    }

    @Test
    public void findAllShouldReturnListOfUsers() {
        List<UserEntity> found = singletonList(USER_ENTITY);
        List<User> expected = singletonList(USER);
        when(userDao.findAll(anyInt(), anyInt())).thenReturn(found);
        when(userMapper.mapUserEntityToUser(USER_ENTITY)).thenReturn(USER);

        List<User> actual = userGenericServiceImpl.findAll(1, 2);
        assertThat("Finding all is failed", actual, is(expected));
    }

    @Test
    public void numberOfRowsIsSameInDaoAndService() {
        when(userDao.getNumberOfRows()).thenReturn(5);
        int actual = userGenericServiceImpl.getNumberOfRows();
        assertThat("Counting number of rows is failed", actual, is(5));
    }
}
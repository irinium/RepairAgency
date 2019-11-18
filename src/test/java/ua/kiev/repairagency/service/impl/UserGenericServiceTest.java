package ua.kiev.repairagency.service.impl;

import org.junit.Before;
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
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import java.sql.SQLException;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserGenericServiceTest {
    private static final String PASSWORD = "password";


    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private UserGenericService userGenericService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserDao userDao;
    @Mock
    private Validator validator;
    @Mock
    private UserMapper userMapper;

    @Before
    public void initMocks() {
        reset(validator, userMapper, userDao, passwordEncoder);
    }

    @Test
    public void registrationShouldNotReturnNull() {
        User user1 = User.builder().build();
        when(userDao.save(any(UserEntity.class))).thenReturn(1);
        User user = userGenericService.register(user1);
        assertNotNull(user);
    }

    @Test(expected = Exception.class)
    public void loginShouldThrownExceptionWithIllegalArguments() {
        when(userDao.findByEmail("null")).thenThrow(NullPointerException.class);
        assertThat(userGenericService.login("null", "null"), null);
    }

    @Test
    public void updateShouldCallUpdateMethodInDao() {
        User user = User.builder().build();
        String password = "password";
        userGenericService.updatePassword(user, password);
        verify(userDao, times(1)).update(userMapper.mapUserToUserEntity(user), password);
    }

    @Test
    public void whenDaoReturnedEmptyListServiseReturnedToo() {
        when(userDao.findAll(1, 5)).thenReturn(emptyList());
        assertEquals(emptyList(), userGenericService.findAll(1, 5));
    }

    @Test
    public void numberOfRowsIsSameInDaoAndService() throws SQLException {
        when(userDao.getNumberOfRows()).thenReturn(5);
        assertEquals(5, userGenericService.getNumberOfRows());
    }
}
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
import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.exception.EntityNotFoundException;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserGenericServiceTest {
    private static final String PASSWORD = "password";
    private static final User USER = new User.UserBuilder()
            .withId(9L)
            .withEmail("alex9999@gmail.com")
            .withPassword("alex9999")
            .withName("s")
            .withSurname("s")
            .withRole(Role.CUSTOMER)
            .build();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    UserGenericService userGenericService;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserDao userDao;
    @Mock
    Validator validator;
    @Mock
    UserMapper userMapper;

    @Before
    public void initMocks() {
        reset(validator, userMapper, userDao, passwordEncoder);
    }

    @Test
    public void registrationShouldNotReturnNull() {
        when(userDao.save(any(UserEntity.class))).thenReturn(1);
        User user = userGenericService.register(USER);
        assertNotNull(user);
    }


    @Test(expected=EntityNotFoundException.class)
    public void loginShouldThrownExceptionWithIllegalArguments() {
       when(userDao.findByEmail("null")).thenReturn(Optional.empty());
        userGenericService.login("null","null");
    }

    @Test
    public void updateShouldThrownExceptionWithIllegalArguments() {
        //doThrow(new Exception()).when(userGenericService).update(isA(User.class),null);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void getNumberOfRows() {
    }
}
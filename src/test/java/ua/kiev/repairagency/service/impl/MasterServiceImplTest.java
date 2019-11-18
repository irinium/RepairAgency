package ua.kiev.repairagency.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.MasterService;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MasterServiceImplTest {
    private MasterService masterService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper userMapper;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Validator validator;

    @Before
    public void init() {
        masterService = new MasterServiceImpl(passwordEncoder,userDao,validator,userMapper,orderMapper,orderDao);
    }

    @Test(expected = Exception.class)
    public void loginShouldThrownExceptionWithIllegalArguments() {
        when(userDao.findByEmail("null")).thenThrow(NullPointerException.class);
        assertThat(masterService.login("null", "null"), null);
    }

    @Test
    public void acceptOrderHaveBeenCalledAtLeastOneTime() {
        Order order = Order.builder().build();
        User user = User.builder().build();
        masterService.acceptOrder(order,user);
        verify(orderDao, times(1)).updateByMaster(orderMapper.mapOrderToOrderEntity(order), user.getId());
    }

    @Test
    public void updateShouldCallUpdateMethodInDao() {
        User user = User.builder().build();
        String password = "password";
        masterService.updatePassword(user, password);
        verify(userDao, times(1)).update(userMapper.mapUserToUserEntity(user), password);
    }
}
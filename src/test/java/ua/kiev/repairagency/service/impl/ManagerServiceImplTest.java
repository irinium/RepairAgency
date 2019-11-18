package ua.kiev.repairagency.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.ApplianceDao;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.Validator;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceImplTest {
    private ManagerService managerService;

    @Mock
    private ApplianceDao applianceDao;

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
        managerService = new ManagerServiceImpl(userDao, applianceDao, orderDao,
                passwordEncoder, validator, userMapper, orderMapper);
    }

    @Test
    public void whenDaoReturnedEmptyListServiseReturnedToo() {
        when(userDao.findAll(1, 5)).thenReturn(emptyList());
        assertEquals(emptyList(), managerService.findAll(1, 5));
    }

    @Test
    public void registrationShouldNotReturnNull() {
        User user = User.builder().build();
        when(userDao.save(any(UserEntity.class))).thenReturn(1);
        User user1 = managerService.register(user);
        assertNotNull(user);
    }

    @Test(expected = Exception.class)
    public void loginShouldThrownExceptionWithIllegalArguments() {
        when(userDao.findByEmail("null")).thenThrow(NullPointerException.class);
        assertThat(managerService.login("null", "null"), null);
    }

    @Test
    public void updateShouldCallUpdateMethodInDao() {
        User user = User.builder().build();
        String password = "password";
        managerService.updatePassword(user, password);
        verify(userDao, times(1)).update(userMapper.mapUserToUserEntity(user), password);
    }

    @Test
    public void findUserByIdShouldReturnEmptyWhenUserIsAbsent() {
        when(userDao.findById(anyLong()).map(userMapper::mapUserEntityToUser)).thenReturn(Optional.empty());
        assertEquals(managerService.findUserById(anyLong()), Optional.empty());
    }

    @Test
    public void findOrderByIdShouldReturnEmptyWhenOrderIsAbsent() {
        when(orderDao.findById(anyLong()).map(orderMapper::mapOrderEntityToOrder)).thenReturn(Optional.empty());
        assertEquals(managerService.findOrderById(anyLong()), Optional.empty());
    }

    @Test
    public void findUserByEmailShouldReturnEmptyWhenUserIsAbsent() {
        when(userDao.findByEmail(anyString()).map(userMapper::mapUserEntityToUser)).thenReturn(Optional.empty());
        assertEquals(managerService.findUserByEmail(anyString()), Optional.empty());
    }

    @Test
    public void setPriceHaveBeenCalledAtLeastOneTime() {
        Double price = 0D;
        Order order = Order.builder().build();
        managerService.setPrice(order,price);
        verify(orderDao, times(1)).updateByPrice(orderMapper.mapOrderToOrderEntity(order), price);
    }

    @Test
    public void acceptOrderHaveBeenCalledAtLeastOneTime() {
        Order order = Order.builder().build();
        managerService.acceptOrder(order);
        verify(orderDao, times(1)).updateByState(orderMapper.mapOrderToOrderEntity(order), true);
    }

    @Test
    public void rejectOrderHaveBeenCalledAtLeastOneTime() {
        Order order = Order.builder().build();
        managerService.rejectOrder(order);
        verify(orderDao, times(1)).updateByState(orderMapper.mapOrderToOrderEntity(order), false);
    }

    @Test
    public void setCommentsToRejectedOrderHaveBeenCalledAtLeastOneTime() {
        Order order = Order.builder().build();
        managerService.setCommentsToRejectedOrder(order,"");
        verify(orderDao, times(1)).update(orderMapper.mapOrderToOrderEntity(order), "");
    }
}
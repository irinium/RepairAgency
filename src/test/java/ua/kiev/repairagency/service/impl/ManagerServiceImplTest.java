package ua.kiev.repairagency.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.encoder.PasswordEncoder;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;
import ua.kiev.repairagency.service.validator.UserValidator;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceImplTest {
    private static final User USER = User.builder().build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().build();
    private static final Order ORDER = Order.builder().build();
    private static final OrderEntity ORDER_ENTITY = OrderEntity.builder().build();

    @InjectMocks
    private ManagerServiceImpl managerService;

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
    private UserValidator userValidator;

    @Before
    public void init() {
        managerService = new ManagerServiceImpl(userDao, orderDao,
                passwordEncoder, userValidator, userMapper, orderMapper);
    }


    @Test
    public void findUserByIdShouldReturnEmptyWhenUserIsAbsent() {
        when(userDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThat("Finding user by id is failed",managerService.findUserById(anyLong()), is(Optional.empty()));
    }

    @Test
    public void findUserByIdShouldReturnUser() {
        when(userDao.findById(USER_ENTITY.getId())).thenReturn(Optional.of(USER_ENTITY));
        when(userMapper.mapUserEntityToUser(USER_ENTITY)).thenReturn(USER);
        assertThat("Finding user by id is failed",managerService.findUserById(USER.getId()), is(Optional.of(USER)));
    }

    @Test
    public void findOrderByIdShouldReturnEmptyWhenOrderIsAbsent() {
        when(orderDao.findById(anyLong())).thenReturn(Optional.empty());
        assertThat("Finding order by id is failed",managerService.findOrderById(anyLong()), is(Optional.empty()));
    }

    @Test
    public void findOrderByIdShouldReturnOrder() {
        when(orderDao.findById(ORDER_ENTITY.getId())).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderMapper.mapOrderEntityToOrder(ORDER_ENTITY)).thenReturn(ORDER);
        assertThat("Finding order by id is failed",managerService.findOrderById(ORDER.getId()), is(Optional.of(ORDER)));
    }

    @Test
    public void findUserByEmailShouldReturnEmptyWhenUserIsAbsent() {
        when(userDao.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThat("Finding user by email is failed",managerService.findUserByEmail(anyString()), is(Optional.empty()));
    }

    @Test
    public void findUserByEmailShouldReturnUser() {
        when(userDao.findByEmail(USER_ENTITY.getEmail())).thenReturn(Optional.of(USER_ENTITY));
        when(userMapper.mapUserEntityToUser(USER_ENTITY)).thenReturn(USER);
        assertThat("Finding user by id is failed",managerService.findUserByEmail(USER.getEmail()), is(Optional.of(USER)));
    }


    @Test
    public void setPriceHaveBeenCalledAtLeastOneTime() {
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);
        managerService.setPrice(ORDER,0.00);
        verify(orderDao).updateByPrice(ORDER_ENTITY, 0.00);
    }

    @Test
    public void acceptOrderHaveBeenCalledAtLeastOneTime() {
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);
        managerService.acceptOrder(ORDER);
        verify(orderDao).updateByState(ORDER_ENTITY, true);
    }

    @Test
    public void rejectOrderHaveBeenCalledAtLeastOneTime() {
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);
        managerService.rejectOrder(ORDER);
        verify(orderDao).updateByState(ORDER_ENTITY, false);
    }

    @Test
    public void setCommentsToRejectedOrderHaveBeenCalledAtLeastOneTime() {
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);
        managerService.setCommentsToRejectedOrder(ORDER,"");
        verify(orderDao).update(ORDER_ENTITY, "");
    }
}
package ua.kiev.repairagency.service.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.UserMapper;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MasterServiceImplTest {
    private static final Order ORDER = Order.builder().build();
    private static final OrderEntity ORDER_ENTITY = OrderEntity.builder().build();
    private static final User USER = User.builder().build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().build();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private MasterServiceImpl masterService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private UserMapper userMapper;

    @Mock
    private OrderMapper orderMapper;

    @Test
    public void acceptOrderHaveBeenCalledAtLeastOneTime() {
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);
        masterService.acceptOrder(ORDER, USER);

        verify(orderDao).updateByMaster(ORDER_ENTITY, USER.getId());
    }

    @Test
    public void completeOrderShouldUpdateOrderByStatus() {
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);
        masterService.completeOrder(ORDER);
        verify(orderDao).updateByStatus(ORDER_ENTITY, false);
    }

    @Test
    public void findMastersOrdersShouldReturnEmptyWhenOrdersAreAbsent() {
        when(orderDao.findMastersOrders(USER_ENTITY, 1, 5)).thenReturn(emptyList());
        when(userMapper.mapUserToUserEntity(USER)).thenReturn(USER_ENTITY);

        assertThat("Finding master's orders is failed",
                masterService.findMastersOrders(USER, 1, 5), is(emptyList()));
    }
}
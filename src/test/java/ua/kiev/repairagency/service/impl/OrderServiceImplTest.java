package ua.kiev.repairagency.service.impl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.dao.ResponseDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.entity.order.OrderEntity;
import ua.kiev.repairagency.service.exception.DataBaseRuntimeException;
import ua.kiev.repairagency.service.mapper.OrderMapper;
import ua.kiev.repairagency.service.mapper.ResponseMapper;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    private static final Order ORDER = Order.builder().build();
    private static final OrderEntity ORDER_ENTITY = OrderEntity.builder().build();
    private static final Double PRICE = 0.00;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private ResponseDao responseDao;

    @Mock
    private ResponseMapper responseMapper;

    @Before
    public void init() {
        orderService = new OrderServiceImpl(orderDao, orderMapper, responseDao, responseMapper);
    }


    @Test
    public void getAllShouldReturnOrdersList() {
        List<OrderEntity> found = singletonList(ORDER_ENTITY);
        List<Order> expected = singletonList(ORDER);
        when(orderDao.findAll(anyInt(), anyInt())).thenReturn(found);
        when(orderMapper.mapOrderEntityToOrder(ORDER_ENTITY)).thenReturn(ORDER);

        List<Order> actual = orderService.getAll(1, 2);
        assertThat("Finding all is failed", actual, is(expected));
    }

    @Test
    public void getNumberOfOrdersRowsShouldThrowsException() {
        exception.expect(DataBaseRuntimeException.class);
        when(orderDao.getNumberOfRows()).thenThrow(DataBaseRuntimeException.class);
        orderService.getNumberOfOrdersRows();
    }

    @Test
    public void getNumberOfResponsesRowsShouldReturnNumberOfRows() {
        when(responseDao.getNumberOfRows()).thenReturn(5);
        assertThat("Counting number of responses rows is failed", orderService.getNumberOfResponsesRows(), is(notNullValue()));
    }

    @Test
    public void updateShouldCallDataBaseUpdating() {
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);
        orderService.update(ORDER, true);
        verify(orderDao).updateByState(ORDER_ENTITY, true);
    }

    @Test
    public void updateShouldUpdatePrice() {
        when(orderMapper.mapOrderToOrderEntity(ORDER)).thenReturn(ORDER_ENTITY);
        orderService.update(ORDER, PRICE);
        verify(orderDao).updateByPrice(ORDER_ENTITY, PRICE);
    }
}
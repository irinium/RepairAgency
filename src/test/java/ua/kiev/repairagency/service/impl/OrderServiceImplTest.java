package ua.kiev.repairagency.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.OrderService;
import ua.kiev.repairagency.service.exception.EmptyDataException;
import ua.kiev.repairagency.service.mapper.OrderMapper;

import java.sql.SQLException;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    private OrderService orderService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderMapper orderMapper;

    @Before
    public void init() {
        orderService = new OrderServiceImpl(orderDao, orderMapper);
    }

    @Test(expected = EmptyDataException.class)
    public void saveShouldThrownExceptionWithEmptyArg() {
        Order order = Order.builder().build();
        when(orderDao.save(orderMapper.mapOrderToOrderEntity(order))).thenThrow(EmptyDataException.class);
        assertThat(orderService.save(order), null);
    }

    @Test
    public void whenDaoReturnedEmptyListServiseReturnedToo() {
        when(orderDao.findAll(1, 5)).thenReturn(emptyList());
        assertEquals(emptyList(), orderService.getAll(1, 5));
    }

    @Test
    public void numberOfRowsIsSameInDaoAndService() throws SQLException {
        when(orderDao.getNumberOfRows()).thenReturn(5);
        assertEquals(5, orderService.getNumberOfRows());
    }
}
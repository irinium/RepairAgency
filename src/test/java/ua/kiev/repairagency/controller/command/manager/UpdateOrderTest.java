package ua.kiev.repairagency.controller.command.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.ManagerService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateOrderTest {

    @Mock
    private ManagerService managerService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UpdateOrder updateOrder;

    @Test
    public void executeShouldUpdateOrderWhenOrderIsAccepted() {
        Order order = Order.builder().withId(1L).build();

        when(request.getParameter("state")).thenReturn("true");
        when(request.getParameter("orderId")).thenReturn("1");
        when(managerService.findOrderById(1L)).thenReturn(order);

        updateOrder.execute(request);
        verify(managerService).acceptOrder(order);
    }

    @Test
    public void executeShouldUpdateOrderWhenOrderIsRejected() {
        Order order = Order.builder().withId(1L).build();

        when(request.getParameter("state")).thenReturn("false");
        when(request.getParameter("orderId")).thenReturn("1");
        when(managerService.findOrderById(1L)).thenReturn(order);

        updateOrder.execute(request);
        verify(managerService).rejectOrder(order);
    }

    @Test
    public void executeShouldReturnOrdersPage(){
        when(request.getParameter("orderId")).thenReturn("1");

        String expected = "/orders.jsp";
        String actual = updateOrder.execute(request);

        assertEquals(expected, actual);
    }
}
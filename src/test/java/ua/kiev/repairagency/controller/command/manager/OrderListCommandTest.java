package ua.kiev.repairagency.controller.command.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderListCommandTest {

    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private OrderListCommand orderListCommand;

    @Test
    public void executeShouldReturnOrderListCommand() {
        when(request.getParameter("currentPage")).thenReturn("1");

        String expected = "/orders.jsp";
        String actual = orderListCommand.execute(request);

        verify(orderService).getAll(1, 5);
        assertEquals(expected, actual);
    }
}
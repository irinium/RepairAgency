package ua.kiev.repairagency.controller.command.master;

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
public class MasterSelectOrdersCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private MasterSelectOrdersCommand selectOrdersCommand;

    @Test
    public void executeShouldReturnOrdersPage() {
        when(request.getParameter("currentPage")).thenReturn("1");

        String expected = "/ordersForMaster.jsp";
        String actual = selectOrdersCommand.execute(request);

        assertEquals(expected, actual);
        verify(orderService).getOrdersWithoutMaster(1, 5);
    }
}
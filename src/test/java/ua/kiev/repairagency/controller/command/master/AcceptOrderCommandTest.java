package ua.kiev.repairagency.controller.command.master;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.MasterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AcceptOrderCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private MasterService masterService;

    @Mock
    private ManagerService managerService;

    @InjectMocks
    private AcceptOrderCommand acceptOrderCommand;

    @Test
    public void executeShouldReturnOrdersForMasterPage() {
        User user = User.builder().withEmail("email@gmail.com").withPassword("password").build();
        Order order = Order.builder().withId(1L).build();

        when(request.getParameter("orderId")).thenReturn("1");
        when(managerService.findOrderById(1L)).thenReturn(order);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        String expected = "/ordersForMaster.jsp";
        String actual = acceptOrderCommand.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldUpdateOrderByMaster() {
        User user = User.builder().withEmail("email@gmail.com").withPassword("password").build();
        Order order = Order.builder().withId(1L).build();

        when(request.getParameter("orderId")).thenReturn("1");
        when(managerService.findOrderById(1L)).thenReturn(order);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        acceptOrderCommand.execute(request);
        verify(masterService).acceptOrder(order,user);
    }
}
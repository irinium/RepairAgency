package ua.kiev.repairagency.controller.command.master;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.MasterService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompleteOrderCommandTest {
    @Mock
    private MasterService masterService;

    @Mock
    private ManagerService managerService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private CompleteOrderCommand completeOrderCommand;

    @Test
    public void execute() {
        Order order = Order.builder().withId(1L).build();

        when(request.getParameter("orderId")).thenReturn("1");
        when(managerService.findOrderById(1L)).thenReturn(order);

        completeOrderCommand.execute(request);
        verify(masterService).completeOrder(order);
    }
}
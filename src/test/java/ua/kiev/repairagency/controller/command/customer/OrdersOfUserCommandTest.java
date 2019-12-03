package ua.kiev.repairagency.controller.command.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.OrderDao;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrdersOfUserCommandTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private OrdersOfUserCommand ordersOfUserCommand;

    @Test
    public void executeShouldReturnOrderListPage() {
        User user = User.builder().withEmail("email@gmail.com").withPassword("password").build();
        UserEntity userEntity = UserEntity.builder().withEmail("email@gmail.com").withPassword("password").build();

        when(request.getParameter("currentPage")).thenReturn("1");
        when(orderService.getNumberOfUserOrdersRows(user)).thenReturn(5);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        String expected = "/userOrderList.jsp";
        String actual = ordersOfUserCommand.execute(request);

        assertEquals(expected, actual);
        verify(customerService).findAllOrders(user,1,5);
    }
}
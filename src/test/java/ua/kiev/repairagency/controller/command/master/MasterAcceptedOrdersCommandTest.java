package ua.kiev.repairagency.controller.command.master;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.entity.user.UserEntity;
import ua.kiev.repairagency.service.MasterService;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MasterAcceptedOrdersCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private OrderService orderService;

    @Mock
    private MasterService masterService;

    @InjectMocks
    private MasterAcceptedOrdersCommand acceptedOrders;

    @Test
    public void executeShouldGaveOrderList() {
        User user = User.builder().withEmail("email@gmail.com").withPassword("password").build();
        UserEntity userEntity = UserEntity.builder().withEmail("email@gmail.com").withPassword("password").build();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("currentPage")).thenReturn("1");
        when(orderService.getNumberOfMasterOrdersRows(user)).thenReturn(5);

        String expected = "/ordersAcceptedByMaster.jsp";
        String actual = acceptedOrders.execute(request);

        assertEquals(expected, actual);
        verify(masterService).findMastersOrders(user, 1, 5);
    }
}
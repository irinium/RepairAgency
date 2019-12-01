package ua.kiev.repairagency.controller.command.authentification;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.controller.command.Pagination;
import ua.kiev.repairagency.dao.ResponseDao;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ShowResponsesCommandTest {

    @InjectMocks
    private ShowResponsesCommand showResponsesCommand;

    @Mock
    private Pagination pagination;
    @Mock
    private OrderService orderService;
    @Mock
    private ResponseDao responseDao;
    @Mock
    private HttpServletRequest request;

    @Ignore
    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(responseDao.findAll(anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(orderService.getNumberOfResponsesRows()).thenReturn(5);

        verify(pagination).setPagination(request,1,5);

        String expected = "/view/responses.jsp";
        String actual = showResponsesCommand.execute(request);

        verify(orderService).getAllResponses(anyInt(), anyInt());
        assertEquals(expected, actual);
    }
}
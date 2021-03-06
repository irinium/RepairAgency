package ua.kiev.repairagency.controller.command.authentification;

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
public class ShowResponsesCommandTest {

    @InjectMocks
    private ShowResponsesCommand showResponsesCommand;

    @Mock
    private OrderService orderService;

    @Mock
    private HttpServletRequest request;

    @Test
    public void executeShouldReturnPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(orderService.getNumberOfResponsesRows()).thenReturn(5);

        String expected = "/responses.jsp";
        String actual = showResponsesCommand.execute(request);

        verify(orderService).getAllResponses(1, 5);
        assertEquals(expected, actual);
    }
}
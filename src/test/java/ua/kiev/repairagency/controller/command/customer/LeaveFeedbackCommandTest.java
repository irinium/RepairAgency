package ua.kiev.repairagency.controller.command.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LeaveFeedbackCommandTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LeaveFeedbackCommand leaveFeedbackCommand;

    @Test
    public void executeShouldSaveFeedback() {
        User user = User.builder().withEmail("email@gmail.com").withPassword("password").build();

        when(request.getParameter("feedback")).thenReturn("feedback");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);


        String actual = leaveFeedbackCommand.execute(request);
        String expected = "/customerHome.jsp";

        assertEquals(expected,actual);
        verify(customerService).createResponse(new Response("feedback", user));
    }
}
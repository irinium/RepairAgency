package ua.kiev.repairagency.controller.command.authentification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.UserGenericService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private UserGenericService userGenericService;

    @InjectMocks
    private RegisterCommand registerCommand;

    @Test
    public void executeShouldReturnRegisterPageWithWrongPassword() {
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("password_two")).thenReturn("wrong");

        String expected = "/login_register.jsp";
        String actual = registerCommand.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnSuccessfulHomePage() {
        HttpSession session = mock(HttpSession.class);
        User user = User.builder().withEmail("email@gmail.com").withPassword("password").build();

        when(request.getParameter(anyString())).thenReturn("password");
        when(request.getSession()).thenReturn(session);

        String expected = "/customerHome.jsp";
        String actual = registerCommand.execute(request);

        assertEquals(expected, actual);
    }
}
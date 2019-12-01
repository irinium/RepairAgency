package ua.kiev.repairagency.controller.command.authentification;

import org.junit.Ignore;
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

        String expected = "view/login_register.jsp";
        String actual = registerCommand.execute(request);

        assertEquals(expected, actual);
    }

    @Ignore
    @Test
    public void executeShouldReturnSuccessfulRegisterPage() {
        HttpSession session = mock(HttpSession.class);
        User user = User.builder().withPassword("password").build();
        when(request.getParameter(anyString())).thenReturn("password");
        when(userGenericService.register(user)).thenReturn(user);

        session.setAttribute("user",user);

        String expected = "view/customerHome.jsp";
        String actual = registerCommand.execute(request);

        assertEquals(expected, actual);
    }
}
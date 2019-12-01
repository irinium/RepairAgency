package ua.kiev.repairagency.controller.command.authentification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.UserGenericService;
import ua.kiev.repairagency.service.exception.InvalidEmailOrPasswordException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private UserGenericService userService;

    @InjectMocks
    LoginCommand loginCommand;

    @Test
    public void executeShouldReturnLoginPageWhenEmailIsInvalid() {
        when(request.getParameter("email")).thenReturn("admin@gmil.com");
        when(userService.login(anyString(), anyString())).thenThrow(InvalidEmailOrPasswordException.class);

        final String actual = loginCommand.execute(request);
        String expected = "view/login_register.jsp";

        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnManagerPageWhenManagerIsLogined() {
        User user = User.builder().withId(1L).withRole(Role.MANAGER).build();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("param");
        when(userService.login(anyString(), anyString())).thenReturn(user);

        String expected = "/view/managerHome.jsp";
        String actual = loginCommand.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnMasterPageWhenMasterIsLogined() {
        User user = User.builder().withId(1L).withRole(Role.MASTER).build();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("param");
        when(userService.login(anyString(), anyString())).thenReturn(user);

        String expected = "/view/masterHome.jsp";
        String actual = loginCommand.execute(request);

        assertEquals(expected, actual);
    }

    @Test
    public void executeShouldReturnCustomerPageWhenCustomerIsLogined() {
        User user = User.builder().withId(1L).withRole(Role.CUSTOMER).build();

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(anyString())).thenReturn("param");
        when(userService.login(anyString(), anyString())).thenReturn(user);

        String expected = "/view/customerHome.jsp";
        String actual = loginCommand.execute(request);

        assertEquals(expected, actual);
    }

}
package ua.kiev.repairagency.controller.command.master;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.MasterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private MasterService masterService;

    @InjectMocks
    private ChangePasswordCommand changePasswordCommand;

    @Test
    public void executeShouldUpdatePassword() {
        User user = User.builder().withEmail("email@gmail.com").withPassword("password").build();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("password")).thenReturn("password");

        changePasswordCommand.execute(request);
        verify(masterService).updatePassword(user, "password");
    }
}

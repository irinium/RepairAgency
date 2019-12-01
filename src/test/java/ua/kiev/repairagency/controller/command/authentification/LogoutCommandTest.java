package ua.kiev.repairagency.controller.command.authentification;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LogoutCommand logoutCommand;

    @Test
    public void executeShouldReturnIndexPage(){
        when(request.getSession()).thenReturn(session);

        final String actual = logoutCommand.execute(request);
        String expected = "view/index.jsp";

        verify(session).invalidate();
        MatcherAssert.assertThat(actual, is(expected));
    }

}
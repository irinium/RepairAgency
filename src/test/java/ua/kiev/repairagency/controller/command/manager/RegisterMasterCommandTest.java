package ua.kiev.repairagency.controller.command.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.service.UserGenericService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterMasterCommandTest {

    @Mock
    private UserGenericService userGenericService;
    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private RegisterMasterCommand registerMasterCommand;

    @Test
    public void executeShouldSaveMasterToDatabase() {
        when(request.getParameter(anyString())).thenReturn("password");

        String expected = "/managerHome.jsp";
        String actual = registerMasterCommand.execute(request);

        assertEquals(expected, actual);
    }
}
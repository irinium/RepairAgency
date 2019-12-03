package ua.kiev.repairagency.controller.command.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.dao.UserDao;
import ua.kiev.repairagency.service.UserGenericService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserListCommandTest {

    @Mock
    private UserGenericService userGenericService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserListCommand userListCommand;

    @Test
    public void executeShouldSaveUserListToDatabase() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(userGenericService.getNumberOfRows()).thenReturn(5);

        String expected = "/listUsers.jsp";
        String actual = userListCommand.execute(request);

        verify(userGenericService).findAll(1, 5);
        assertEquals(expected, actual);
    }
}
package ua.kiev.repairagency.controller.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandHelperTest {

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private CommandHelper commandHelper;

    @Test
    public void getInt() {
        String var = "1";
        int actual = CommandHelper.getInt(var);
        int expected = 1;

        assertEquals(expected,actual);
    }

    @Test
    public void setPagination() {
        int rows = 5;
        int recordsPerPage = 5;
        int nOfPages = rows/recordsPerPage;
        int currentPage = 1;

        CommandHelper.setPagination(request,currentPage,rows);

        verify(request).setAttribute("noOfPages",nOfPages);
        verify(request).setAttribute("currentPage",currentPage);
        verify(request).setAttribute("recordsPerPage",recordsPerPage);
    }
}
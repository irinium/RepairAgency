package ua.kiev.repairagency.controller.command.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.kiev.repairagency.domain.appliance.Appliance;
import ua.kiev.repairagency.domain.appliance.Manufacturer;
import ua.kiev.repairagency.domain.appliance.Type;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MakeOrderCommandTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private MakeOrderCommand makeOrderCommand;

    @Test
    public void executeShouldSaveTheOrder() {
        User user = User.builder().withEmail("email@gmail.com").withPassword("password").build();
        Appliance appliance = Appliance.builder()
                .withName("appliance")
                .withManufacturer(Manufacturer.BOSCH)
                .withType(Type.CLIMATIC_APPLIANCE)
                .withUser(user)
                .build();

        when(request.getParameter("title")).thenReturn("title");
        when(request.getParameter("appliance_name")).thenReturn("appliance");
        when(request.getParameter("type")).thenReturn(String.valueOf(Type.CLIMATIC_APPLIANCE));
        when(request.getParameter("manufacturer")).thenReturn(String.valueOf(Manufacturer.BOSCH));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        makeOrderCommand.execute(request);

        verify(customerService).makeOrder(appliance,user,"title");
    }
}
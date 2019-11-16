package ua.kiev.repairagency.controller.command.user;

import org.apache.log4j.Logger;
import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.appliance.ElectricAppliance;
import ua.kiev.repairagency.domain.appliance.Manufacturer;
import ua.kiev.repairagency.domain.appliance.Type;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MakeOrderCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private final CustomerService customerService;


    public MakeOrderCommand(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String type = request.getParameter("type");
        final String manufacturer = request.getParameter("manufacturer");
        final String applianceName = request.getParameter("appliance_name");
        final String model = request.getParameter("model");
        final String disrepair = request.getParameter("disrepair");
        final String title = request.getParameter("title");

        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");

        customerService.makeOrder(ElectricAppliance.builder()
                        .withId(user.getId())
                        .withName(applianceName)
                        .withType(Type.valueOf(type))
                        .withManufacturer(Manufacturer.valueOf(manufacturer))
                        .withModel(model)
                        .withDisrepair(disrepair)
                        .withUser(user)
                        .build()
                , user, title);

        session.setAttribute("page", "/view/customerHome.jsp");

        return "/view/customerHome.jsp";
    }
}
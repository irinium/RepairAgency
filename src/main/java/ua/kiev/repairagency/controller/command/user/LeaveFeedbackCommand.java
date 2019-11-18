package ua.kiev.repairagency.controller.command.user;

import org.apache.log4j.Logger;
import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LeaveFeedbackCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private final CustomerService customerService;

    public LeaveFeedbackCommand(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String feedback = request.getParameter("feedback");
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute("user");

        customerService.createResponse(new Response( feedback, user));
        session.setAttribute("page", "/view/customerHome.jsp");
        return "/view/customerHome.jsp";
    }
}

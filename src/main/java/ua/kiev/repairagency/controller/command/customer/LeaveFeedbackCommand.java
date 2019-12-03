package ua.kiev.repairagency.controller.command.customer;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;

import javax.servlet.http.HttpServletRequest;

public class LeaveFeedbackCommand implements Command {
    private final CustomerService customerService;

    public LeaveFeedbackCommand(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String feedback = request.getParameter("feedback");
        final User user = (User) request.getSession().getAttribute("user");

        customerService.createResponse(new Response(feedback, user));

        return "/customerHome.jsp";
    }
}

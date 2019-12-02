package ua.kiev.repairagency.controller.command.manager;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.ManagerService;

import javax.servlet.http.HttpServletRequest;

public class UpdateOrder implements Command {
    private final ManagerService managerService;

    public UpdateOrder(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String state = request.getParameter("state");
        final String comments = request.getParameter("comments");
        final String price = request.getParameter("price");
        final String orderId = request.getParameter("orderId");

        Order order = managerService.findOrderById(Long.parseLong(orderId));

        if (!Boolean.parseBoolean(state)) {
            managerService.rejectOrder(order);
            managerService.setCommentsToRejectedOrder(order, comments);
            managerService.setPrice(order, 0.00);
        } else {
            managerService.acceptOrder(order);
            if (price != null) {
                managerService.setPrice(order, Double.parseDouble(price));
            }
        }
        return "/orders.jsp";
    }
}

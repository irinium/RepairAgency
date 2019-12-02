package ua.kiev.repairagency.controller.command.master;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.MasterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AcceptOrderCommand implements Command {
    private final MasterService masterService;
    private final ManagerService managerService;

    public AcceptOrderCommand(MasterService masterService, ManagerService managerService) {
        this.masterService = masterService;
        this.managerService = managerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String orderId = request.getParameter("orderId");
        final HttpSession session = request.getSession();
        final User master = (User) session.getAttribute("user");

        Order order = managerService.findOrderById(Long.parseLong(orderId));

        masterService.acceptOrder(order, master);

        return "/ordersForMaster.jsp";
    }
}

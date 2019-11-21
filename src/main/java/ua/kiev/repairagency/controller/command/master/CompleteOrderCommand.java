package ua.kiev.repairagency.controller.command.master;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.MasterService;

import javax.servlet.http.HttpServletRequest;

public class CompleteOrderCommand implements Command {
    private final MasterService masterService;
    private final ManagerService managerService;

    public CompleteOrderCommand(MasterService masterService, ManagerService managerService) {
        this.masterService = masterService;
        this.managerService = managerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String orderId = request.getParameter("orderId");

        masterService.completeOrder(managerService.findOrderById(Long.parseLong(orderId)));

        return "/view/masterHome.jsp";
    }
}

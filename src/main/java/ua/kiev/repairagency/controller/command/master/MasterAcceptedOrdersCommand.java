package ua.kiev.repairagency.controller.command.master;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.MasterService;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.kiev.repairagency.controller.command.CommandHelper.setPagination;

public class MasterAcceptedOrdersCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final OrderService orderService;
    private final MasterService masterService;


    public MasterAcceptedOrdersCommand(OrderService orderService, MasterService masterService) {
        this.orderService = orderService;
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || Integer.parseInt(currentPageParam) < 1) ? 1 : Integer.parseInt(currentPageParam);

        User master = (User) request.getSession().getAttribute("user");
        int rows = orderService.getNumberOfMasterOrdersRows(master);

        List<Order> orders = masterService.findMastersOrders(master, currentPage, RECORDS_PER_PAGE);

        request.setAttribute("orders", orders);

        setPagination(request, currentPage, rows);

        return "/ordersAcceptedByMaster.jsp";
    }
}

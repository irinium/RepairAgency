package ua.kiev.repairagency.controller.command.master;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.controller.command.Pagination;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.MasterService;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MasterAcceptedOrdersCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final OrderService orderService;
    private final MasterService masterService;
    private final Pagination pagination;


    public MasterAcceptedOrdersCommand(OrderService orderService, MasterService masterService, Pagination pagination) {
        this.orderService = orderService;
        this.masterService = masterService;
        this.pagination = pagination;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");

        int currentPage = (currentPageParam == null || Integer.parseInt(currentPageParam) < 1) ? 1 : Integer.parseInt(currentPageParam);
        int rows = orderService.getNumberOfOrdersRows();
        User master = (User) request.getSession().getAttribute("user");

        List<Order> orders = masterService.findMastersOrders(master, currentPage, RECORDS_PER_PAGE);

        request.setAttribute("orders", orders);

        pagination.setPagination(request, currentPage, rows);

        return "/view/ordersAcceptedByMaster.jsp";
    }
}

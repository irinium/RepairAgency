package ua.kiev.repairagency.controller.command.master;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.kiev.repairagency.controller.command.CommandHelper.getInt;
import static ua.kiev.repairagency.controller.command.CommandHelper.setPagination;

public class MasterSelectOrdersCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final OrderService orderService;

    public MasterSelectOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || getInt(currentPageParam) < 1) ? 1 : getInt(currentPageParam);
        int rows = orderService.getNumberOfOrdersRowsWithoutMaster();

        List<Order> orders = orderService.getOrdersWithoutMaster(currentPage, RECORDS_PER_PAGE);

        request.setAttribute("orders", orders);

        setPagination(request, currentPage, rows);

        return "/ordersForMaster.jsp";
    }
}

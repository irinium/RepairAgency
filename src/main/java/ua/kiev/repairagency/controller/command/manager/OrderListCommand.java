package ua.kiev.repairagency.controller.command.manager;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.kiev.repairagency.controller.command.CommandHelper.getInt;
import static ua.kiev.repairagency.controller.command.CommandHelper.setPagination;

public class OrderListCommand implements Command {
    private final OrderService orderService;
    private static final int RECORDS_PER_PAGE = 5;


    public OrderListCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int rows = orderService.getNumberOfOrdersRows();
        int currentPage = (currentPageParam == null || getInt(currentPageParam) < 1 ||
                getInt(currentPageParam) > 1000000) ? 1 : getInt(currentPageParam);

        List<Order> orders = orderService.getAll(currentPage, RECORDS_PER_PAGE);

        request.setAttribute("orders", orders);

        setPagination(request, currentPage, rows);

        return "/orders.jsp";
    }
}

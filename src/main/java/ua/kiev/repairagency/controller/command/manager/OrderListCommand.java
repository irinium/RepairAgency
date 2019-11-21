package ua.kiev.repairagency.controller.command.manager;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.controller.command.Pagination;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderListCommand implements Command {
    private final OrderService orderService;
    private static final int RECORDS_PER_PAGE = 5;
    private final Pagination pagination;


    public OrderListCommand(OrderService orderService, Pagination pagination) {
        this.orderService = orderService;
        this.pagination = pagination;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int rows = orderService.getNumberOfOrdersRows();
        int currentPage = (currentPageParam == null || getInt(currentPageParam) < 1 ||
                getInt(currentPageParam) > 1000000) ? 1 : getInt(currentPageParam);

        List<Order> orders = orderService.getAll(currentPage, RECORDS_PER_PAGE);
        List<Order> o = orders;
        request.setAttribute("orders", orders);

        pagination.setPagination(request, currentPage, rows);

        return "/view/orders.jsp";
    }

    private int getInt(String currentPageParam){
        int current;
        try {
            current = Integer.parseInt(currentPageParam);
        } catch (NumberFormatException e) {
            current = 1;
        }
        return current;
    }
}

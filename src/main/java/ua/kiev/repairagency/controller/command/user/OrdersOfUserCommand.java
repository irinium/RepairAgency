package ua.kiev.repairagency.controller.command.user;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.controller.command.Pagination;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrdersOfUserCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final Pagination pagination;


    public OrdersOfUserCommand(CustomerService customerService, OrderService orderService, Pagination pagination) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.pagination = pagination;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || getInt(currentPageParam) < 1) ? 1 : getInt(currentPageParam);
        int rows = orderService.getNumberOfOrdersRows();
        User user = (User)request.getSession().getAttribute("user");

        List<Order> orders = customerService.findAllOrders(user, currentPage, RECORDS_PER_PAGE);

        request.setAttribute("userOrders", orders);

        pagination.setPagination(request,currentPage,rows);

        return "/view/userOrderList.jsp";
    }

    private int getInt(String currentPageParam) {
        int current;
        try {
            current = Integer.parseInt(currentPageParam);
        } catch (NumberFormatException e) {
            current = 1;
        }
        return current;
    }
}

package ua.kiev.repairagency.controller.command.customer;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.kiev.repairagency.controller.command.CommandHelper.getInt;
import static ua.kiev.repairagency.controller.command.CommandHelper.setPagination;

public class OrdersOfUserCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final CustomerService customerService;
    private final OrderService orderService;


    public OrdersOfUserCommand(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || getInt(currentPageParam) < 1) ? 1 : getInt(currentPageParam);
        User user = (User) request.getSession().getAttribute("user");
        int rows = orderService.getNumberOfUserOrdersRows(user);

        List<Order> orders = customerService.findAllOrders(user, currentPage, RECORDS_PER_PAGE);

        request.setAttribute("userOrders", orders);

        setPagination(request, currentPage, rows);

        return "/userOrderList.jsp";
    }
}

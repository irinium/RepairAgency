package ua.kiev.repairagency.controller;

import ua.kiev.repairagency.context.ApplicationContextInjector;
import ua.kiev.repairagency.controller.command.Pagination;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.CustomerService;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserOrderListServlet extends HttpServlet {
    private final CustomerService customerService;
    private final OrderService orderService;
    private static final int RECORDS_PER_PAGE = 5;
    private final Pagination pagination;


    public UserOrderListServlet() {
        this.customerService = ApplicationContextInjector.getCustomerService();
        this.orderService = ApplicationContextInjector.getOrderService();
        this.pagination = ApplicationContextInjector.getPAGINATION();
    }


    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || Integer.parseInt(currentPageParam) < 1) ? 1 : Integer.parseInt(currentPageParam);
        int rows = orderService.getNumberOfRows();
        User user = (User)request.getSession().getAttribute("user");

        List<Order> orders = customerService.findAllOrders(user, currentPage, RECORDS_PER_PAGE);

        request.setAttribute("userOrders", orders);

        pagination.setPagination(request,currentPage,rows);

        request.getRequestDispatcher("/view/userOrderList.jsp").forward(request, response);
    }
}

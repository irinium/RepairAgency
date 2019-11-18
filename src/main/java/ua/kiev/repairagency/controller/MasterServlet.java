package ua.kiev.repairagency.controller;

import ua.kiev.repairagency.context.ApplicationContextInjector;
import ua.kiev.repairagency.controller.command.Pagination;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MasterServlet extends HttpServlet {
    private final OrderService orderService;
    private static final int RECORDS_PER_PAGE = 5;
    private final Pagination pagination;


    public MasterServlet() {
        this.orderService = ApplicationContextInjector.getOrderService();
        this.pagination = ApplicationContextInjector.getPAGINATION();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || Integer.parseInt(currentPageParam) < 1) ? 1 : Integer.parseInt(currentPageParam);
        int rows = orderService.getNumberOfRows();

        List<Order> orders = orderService.getAll(currentPage, RECORDS_PER_PAGE);

        request.setAttribute("orders", orders);

        pagination.setPagination(request, currentPage, rows);

        request.getRequestDispatcher("/view/ordersForMaster.jsp").forward(request, response);
    }
}

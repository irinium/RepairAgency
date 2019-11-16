package ua.kiev.repairagency.controller;

import ua.kiev.repairagency.context.ApplicationContextInjector;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderListServlet extends HttpServlet {
    private OrderService orderService;
    private static final int RECORDS_PER_PAGE = 5;


    public OrderListServlet() {
        this.orderService = ApplicationContextInjector.getOrderService();
    }


    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || Integer.parseInt(currentPageParam) < 1) ? 1 : Integer.parseInt(currentPageParam);


        List<Order> orders = orderService.getAll(currentPage, RECORDS_PER_PAGE);

        request.setAttribute("orders", orders);

        int rows = orderService.getNumberOfRows();

        int nOfPages = rows / RECORDS_PER_PAGE;

        if (nOfPages % RECORDS_PER_PAGE > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", RECORDS_PER_PAGE);

        request.getRequestDispatcher("/view/orders.jsp").forward(request, response);
    }
}

package ua.kiev.repairagency.controller;

import ua.kiev.repairagency.context.ApplicationContextInjector;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.OrderService;
import ua.kiev.repairagency.service.impl.UserGenericService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListEntities", urlPatterns = {"/ListEntities"})
public class ListServlet extends HttpServlet {
    private ApplicationContextInjector applicationContextInjector;
    private UserGenericService userGenericService;
    private OrderService orderService;

    public ListServlet() {
        this.applicationContextInjector = ApplicationContextInjector.getInstance();
        this.userGenericService = ApplicationContextInjector.getUserGenericService();
        this.orderService = ApplicationContextInjector.getOrderService();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

        List<User> users = userGenericService.findAll(currentPage, recordsPerPage);

        request.setAttribute("users", users);

        int rows = userGenericService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/listUsers.jsp");
        dispatcher.forward(request, response);
    }
}

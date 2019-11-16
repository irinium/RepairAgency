package ua.kiev.repairagency.controller.command.user;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Order;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderListCommand implements Command {
    private OrderService orderService;
    private static final int RECORDS_PER_PAGE = 5;


    public OrderListCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
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

        return "/view/userOrderList.jsp";
    }
}

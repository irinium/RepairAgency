package ua.kiev.repairagency.controller.command.authentification;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.controller.command.Pagination;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ShowResponsesCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final OrderService orderService;
    private final Pagination pagination;


    public ShowResponsesCommand(OrderService orderService, Pagination pagination) {
        this.orderService = orderService;
        this.pagination = pagination;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || getInt(currentPageParam) < 1) ? 1 : getInt(currentPageParam);
        int rows = orderService.getNumberOfResponsesRows();

        List<Response> responses = orderService.getAllResponses(currentPage, RECORDS_PER_PAGE);

        request.setAttribute("responses", responses);

        pagination.setPagination(request, currentPage, rows);

        return "/view/responses.jsp";
    }

    private int getInt(String currentPageParam) {
        int current;
        try {
            current = Integer.parseInt(currentPageParam);
        } catch (NumberFormatException e) {
            current = -1;
        }
        return current;
    }
}

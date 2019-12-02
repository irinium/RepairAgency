package ua.kiev.repairagency.controller.command.authentification;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.order.Response;
import ua.kiev.repairagency.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.kiev.repairagency.controller.command.CommandHelper.getInt;
import static ua.kiev.repairagency.controller.command.CommandHelper.setPagination;


public class ShowResponsesCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final OrderService orderService;


    public ShowResponsesCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || getInt(currentPageParam) < 1) ? 1 : getInt(currentPageParam);
        int rows = orderService.getNumberOfResponsesRows();

        List<Response> responses = orderService.getAllResponses(currentPage, RECORDS_PER_PAGE);

        request.setAttribute("responses", responses);

        setPagination(request, currentPage, rows);

        return "/responses.jsp";
    }
}

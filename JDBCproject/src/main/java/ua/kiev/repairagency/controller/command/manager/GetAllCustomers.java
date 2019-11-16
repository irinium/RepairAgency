package ua.kiev.repairagency.controller.command.manager;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.impl.UserGenericService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetAllCustomers implements Command {
    private UserGenericService userGenericService;
    private static final int RECORDS_PER_PAGE = 5;


    public GetAllCustomers(UserGenericService userGenericService) {
        this.userGenericService = userGenericService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || Integer.parseInt(currentPageParam) < 1) ? 1 : Integer.parseInt(currentPageParam);

        List<User> users = userGenericService.findAll(currentPage, RECORDS_PER_PAGE);

        request.setAttribute("users", users);

        int rows = userGenericService.getNumberOfRows();

        int nOfPages = rows / RECORDS_PER_PAGE;

        if (nOfPages % RECORDS_PER_PAGE > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", RECORDS_PER_PAGE);

        return "/view/listUsers.jsp";
    }
}

package ua.kiev.repairagency.controller.command.manager;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.controller.command.Pagination;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.UserGenericService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserListCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final UserGenericService userGenericService;
    private final Pagination pagination;


    public UserListCommand(UserGenericService userGenericService, Pagination pagination) {
        this.userGenericService = userGenericService;
        this.pagination = pagination;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || getInt(currentPageParam) < 1) ? 1 : getInt(currentPageParam);
        int rows = userGenericService.getNumberOfRows();

        List<User> users = userGenericService.findAll(currentPage, RECORDS_PER_PAGE);

        request.setAttribute("users", users);

        pagination.setPagination(request, currentPage, rows);

        return "/view/listUsers.jsp";
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

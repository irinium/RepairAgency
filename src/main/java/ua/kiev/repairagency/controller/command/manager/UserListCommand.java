package ua.kiev.repairagency.controller.command.manager;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.UserGenericService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.kiev.repairagency.controller.command.CommandHelper.getInt;
import static ua.kiev.repairagency.controller.command.CommandHelper.setPagination;

public class UserListCommand implements Command {
    private static final int RECORDS_PER_PAGE = 5;
    private final UserGenericService userGenericService;


    public UserListCommand(UserGenericService userGenericService) {
        this.userGenericService = userGenericService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = (currentPageParam == null || getInt(currentPageParam) < 1) ? 1 : getInt(currentPageParam);
        int rows = userGenericService.getNumberOfRows();

        List<User> users = userGenericService.findAll(currentPage, RECORDS_PER_PAGE);

        request.setAttribute("users", users);

        setPagination(request, currentPage, rows);

        return "/listUsers.jsp";
    }
}

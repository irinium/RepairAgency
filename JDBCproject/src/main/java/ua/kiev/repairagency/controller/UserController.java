package ua.kiev.repairagency.controller;

import ua.kiev.repairagency.context.ApplicationContextInjector;
import ua.kiev.repairagency.service.impl.UserGenericService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

    private final UserGenericService userService;

    public UserController() {
        this.userService = ApplicationContextInjector.getUserGenericService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  final List<User> users = userService.findAll(int currentPage, int recordsPerPage);

      //  req.setAttribute("users", users);

        req.getRequestDispatcher("view/users.jsp").forward(req, resp);
    }
}

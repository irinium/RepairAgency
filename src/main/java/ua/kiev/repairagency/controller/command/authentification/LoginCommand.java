package ua.kiev.repairagency.controller.command.authentification;

import org.apache.log4j.Logger;
import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.UserGenericService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private final UserGenericService userService;

    public LoginCommand(UserGenericService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        final HttpSession session = request.getSession();

        User user = userService.login(email, password);

        if (user != null) {
            session.setAttribute("user", user);

            if (user.getRole() == Role.MANAGER) {
                return "managerHome.jsp";
            } else if (user.getRole() == Role.MASTER) {
                return "masterHome.jsp";
            } else {
                return "customerHome.jsp";
            }
        } else {
            LOGGER.info("User hasn't been authenticated");
            return "/login_register.jsp";
        }
    }
}

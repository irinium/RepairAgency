package ua.kiev.repairagency.controller.command.authentification;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.ManagerService;
import ua.kiev.repairagency.service.UserGenericService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class RegisterCommand implements Command {

    private final UserGenericService userGenericService;
    private final ManagerService managerService;

    public RegisterCommand(UserGenericService userGenericService, ManagerService managerService) {
        this.userGenericService = userGenericService;
        this.managerService = managerService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final String email = request.getParameter("email");
        final String phone = request.getParameter("phone");
        final String password1 = request.getParameter("password");
        final String password2 = request.getParameter("password_two");

        final HttpSession session = request.getSession();

        if (Objects.equals(password1, password2)) {
            User user = userGenericService.register(User.builder()
                    .withEmail(email)
                    .withName(name)
                    .withPassword(password1)
                    .withPhoneNumber(phone)
                    .withSurname(surname)
                    .withRole(Role.CUSTOMER)
                    .build());
            session.setAttribute("user", user);
            return "/customerHome.jsp";
        }
        return "/login_register.jsp";
    }
}

package ua.kiev.repairagency.controller.command.manager;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.UserGenericService;

import javax.servlet.http.HttpServletRequest;

public class RegisterMasterCommand implements Command {
    private final UserGenericService userGenericService;

    public RegisterMasterCommand(UserGenericService userGenericService) {
        this.userGenericService = userGenericService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String name = request.getParameter("master_name");
        final String surname = request.getParameter("surname");
        final String email = request.getParameter("master_email");
        final String phone = request.getParameter("phone");
        final String password1 = request.getParameter("password");
        final String password2 = request.getParameter("password_two");

        if (password1.equals(password2)) {
            User user = User.builder()
                    .withEmail(email)
                    .withName(name)
                    .withPassword(password1)
                    .withPhoneNumber(phone)
                    .withSurname(surname)
                    .withRole(Role.MASTER)
                    .build();
            userGenericService.register(user);
        }
        return "/managerHome.jsp";
    }
}

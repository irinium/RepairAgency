package ua.kiev.repairagency.controller.command.master;

import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.domain.user.User;
import ua.kiev.repairagency.service.MasterService;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordCommand implements Command {
    private final MasterService masterService;

    public ChangePasswordCommand(MasterService masterService) {
        this.masterService = masterService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String password = request.getParameter("password");
        final User master = (User) request.getSession().getAttribute("user");

        if (password != null) {
            masterService.updatePassword(master, password);
        }

        return "/masterHome.jsp";
    }
}

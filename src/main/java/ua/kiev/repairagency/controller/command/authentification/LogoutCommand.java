package ua.kiev.repairagency.controller.command.authentification;

import ua.kiev.repairagency.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.invalidate();

        return "/index.jsp";
    }
}

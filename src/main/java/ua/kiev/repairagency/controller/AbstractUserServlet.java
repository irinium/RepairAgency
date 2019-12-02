package ua.kiev.repairagency.controller;

import ua.kiev.repairagency.context.ApplicationContextInjector;
import ua.kiev.repairagency.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AbstractUserServlet extends HttpServlet {
    protected Map<String, Command> commandNameToCommand;

    public AbstractUserServlet(String commandName) {
        this.commandNameToCommand = initCommands(commandName);
    }

    private Map<String, Command> initCommands(String commandName) {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        switch (commandName) {
            case "customer":
                return injector.getCustomerCommands();
            case "master":
                return injector.getMasterCommands();
            case "manager":
                return injector.getManagerCommands();
            case "auth":
                return injector.getAuthenticationCommands();
            default:
                throw new IllegalArgumentException("Illegal command");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp, commandNameToCommand);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp, commandNameToCommand);
    }

    public static void doProcess(HttpServletRequest req, HttpServletResponse resp, Map<String, Command> commandNameToCommand) throws ServletException, IOException {
        final String commandName = req.getParameter("command");
        final Command defaultCommand = request -> "errors/error404.jsp";

        String page = commandNameToCommand.getOrDefault(commandName, defaultCommand).execute(req);

        if ("login".equals(commandName) || "register".equals(commandName) || "logout".equals(commandName)) {
            resp.sendRedirect(page);
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}

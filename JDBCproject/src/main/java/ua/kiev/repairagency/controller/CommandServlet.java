package ua.kiev.repairagency.controller;

import org.apache.log4j.Logger;
import ua.kiev.repairagency.controller.command.Command;
import ua.kiev.repairagency.context.ApplicationContextInjector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CommandServlet extends HttpServlet { //TODO rename
    private final static Logger LOGGER = Logger.getLogger(CommandServlet.class);
    private final Map<String, Command> commandNameToCommand;
    private final Command defaultCommand;

    public CommandServlet() {
        final ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        this.commandNameToCommand = injector.getUserCommands();
        this.defaultCommand = request -> "view/problem.jsp";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String commandName = req.getParameter("command");
        LOGGER.info(commandName);

        String page;
        if (commandName!=null){
            page =  commandNameToCommand.get(commandName).execute(req);
        }else {
            page = commandNameToCommand.get(defaultCommand).execute(req);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

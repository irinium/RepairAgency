package ua.kiev.repairagency.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);
}

package ua.kiev.repairagency.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/manager")
public class ManagerServlet extends AbstractUserServlet {

    public ManagerServlet() {
        super("manager");
    }
}

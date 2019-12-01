package ua.kiev.repairagency.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/master")
public class MasterServlet extends AbstractUserServlet {

    public MasterServlet() {
        super("master");
    }
}

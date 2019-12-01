package ua.kiev.repairagency.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/customer")
public class CustomerServlet extends AbstractUserServlet {

    public CustomerServlet() {
       super("customer");
    }
}

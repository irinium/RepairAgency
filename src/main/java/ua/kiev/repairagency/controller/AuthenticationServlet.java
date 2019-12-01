package ua.kiev.repairagency.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/auth")
public class AuthenticationServlet extends AbstractUserServlet {

    public AuthenticationServlet() {
        super("auth");
    }
}

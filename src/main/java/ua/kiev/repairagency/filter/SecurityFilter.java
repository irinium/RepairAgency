package ua.kiev.repairagency.filter;

import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(dispatcherTypes = {
        DispatcherType.REQUEST,
        DispatcherType.FORWARD},
        urlPatterns = {"/customerHome.jsp",
                "/home.jsp",
                "/listUsers.jsp",
                "/login_register.jsp",
                "/managerHome.jsp",
                "/masterHome.jsp",
                "/orders.jsp",
                "/ordersAcceptedByMaster.jsp",
                "/ordersForMaster.jsp",
                "/userOrdersList.jsp"})
public class SecurityFilter implements Filter {
    public static final String ERROR_403 = "/view/errors/error403.jsp";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String registerUrl = "/login_register.jsp";
        final String indexUrl = "/index.jsp";
        final String homePageUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/";

        String command = request.getParameter("command");

        final User user = (User) request.getSession().getAttribute("user");
        final String currentUrl = request.getRequestURL().toString();

        if (!"login".equals(command) && !"logout".equals(command) && !"register".equals(command)) {
            if (currentUrl.contains("/manager") &&
                    (user == null || user.getRole() != Role.MANAGER)) {
                request.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
                return;
            }
            if (currentUrl.contains("/customer") &&
                    (user == null || user.getRole() != Role.CUSTOMER)) {
                request.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
                return;
            }
            if (currentUrl.contains("/master") &&
                    (user == null || user.getRole() != Role.MASTER)) {
                request.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
                return;
            }
            if (currentUrl.equals(homePageUrl) ||
                    currentUrl.contains(indexUrl) ||
                    currentUrl.contains(registerUrl)) {
                if (user != null && user.getRole() == Role.CUSTOMER) {
                    response.sendRedirect("/customer");
                    return;
                }
                if (user != null && user.getRole() == Role.MANAGER) {
                    response.sendRedirect("/manager");
                    return;
                }
                if (user != null && user.getRole() == Role.MASTER) {
                    response.sendRedirect("/master");
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

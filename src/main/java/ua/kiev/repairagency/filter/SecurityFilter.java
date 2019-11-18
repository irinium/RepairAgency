package ua.kiev.repairagency.filter;

import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {//TODO complete the security filter code

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        Object requestedPage = request.getSession().getAttribute("page");

        if (requestedPage == null) {
            filterChain.doFilter(request, response);
        } else {
            String page = requestedPage.toString();
            if (user != null && user.getRole().equals(Role.CUSTOMER)) {
                if (page.equals("/view/managerHome.jsp") || page.equals("/view/masterHome.jsp")) {
                    response.sendRedirect("/view/error403.jsp");
                    return;
                }
            } else if (user != null && user.getRole().equals(Role.MASTER)) {
                if (page.equals("/view/managerHome.jsp") || page.equals("view/customerHome.jsp")) {
                    response.sendRedirect("/view/error403.jsp");
                    return;
                }
            }
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}

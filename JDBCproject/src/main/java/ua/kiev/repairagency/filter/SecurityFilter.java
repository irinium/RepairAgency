package ua.kiev.repairagency.filter;

import ua.kiev.repairagency.domain.user.Role;
import ua.kiev.repairagency.domain.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SecurityFilter implements Filter {//TODO complete the security filter code
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)servletRequest;

        final User user = (User) request.getSession().getAttribute("user");
        if (user.getRole() == Role.MANAGER) {
            request.getRequestDispatcher("view/managerHome").forward(request, servletResponse);
        }else if (user.getRole() == Role.CUSTOMER){
            request.getRequestDispatcher("view/customerHome").forward(request,servletResponse);
        }else {
            request.getRequestDispatcher("view/masterHome.jsp").forward(request, servletResponse);
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

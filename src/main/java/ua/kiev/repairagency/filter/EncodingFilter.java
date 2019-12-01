package ua.kiev.repairagency.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
    private static final String LOCALE = "locale";
    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        servletRequest.setCharacterEncoding(ENCODING);

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getParameter(LOCALE) != null) {
            req.getSession().setAttribute(LOCALE, req.getParameter(LOCALE));
        }

        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

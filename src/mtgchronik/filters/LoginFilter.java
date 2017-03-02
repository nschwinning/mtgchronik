package mtgchronik.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtgchronik.webfrontend.UserController;

public class LoginFilter implements Filter {

    private static final String LOGIN_TARGET = "/login.xhtml";

    @Inject
    private UserController userController;
    
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(final ServletRequest request,
            final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        if (userController == null) {
        	System.out.println("UserController is null");
            redirectToLoginPage(req, res);
        } else if (userController.getCurrentUser()==null) {
            //loginView.setRequestedPage(req.getRequestURI().replace(URI_PREFIX, ""));
        	System.out.println("User is null");
        	userController.setRequestedPage(req.getRequestURI());
            redirectToLoginPage(req, res);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private void redirectToLoginPage(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + LOGIN_TARGET);
    }
}
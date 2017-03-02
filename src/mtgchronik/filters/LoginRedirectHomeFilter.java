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
import javax.servlet.http.HttpSession;

import mtgchronik.webfrontend.UserController;

public class LoginRedirectHomeFilter implements Filter {

	@Inject
	private UserController userController;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;

		// get the user information bean
		final HttpSession session = req.getSession();

		// if there is a session and user is logged in, redirect to home.xhtml
		if (session != null && userController != null && userController.getCurrentUser() != null) {
			final String contextPath = req.getContextPath();

			final String redirect = contextPath + "/administration/administration.xhtml";

			res.sendRedirect(redirect);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

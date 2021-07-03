package javaservlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter.init()");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("MyFilter.doFilter()");

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession(true);

		// Avoid post resubmit transactions
		httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);

		if (session.getAttribute("user") != null || httpServletRequest.getServletPath().equals("/error.htm")
				|| httpServletRequest.getServletPath().equals("/login.htm")) {
			chain.doFilter(request, response);
		} else {
			httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Validation Message: Authentication Error");
		}

	}

	@Override
	public void destroy() {
		System.out.println("MyFilter.destroy()");
	}

}

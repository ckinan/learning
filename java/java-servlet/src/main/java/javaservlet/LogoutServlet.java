package javaservlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {

	public void init() {
		System.out.println("LogoutServlet.init()");
	}

	public void destroy() {
		System.out.println("LogoutServlet.destroy()");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("LogoutServlet.doGet()");
		HttpSession session = request.getSession(true);
		session.setAttribute("user", null);
		response.sendRedirect("login.htm");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("LogoutServlet.doPost()");
		doGet(request, response);
	}

}

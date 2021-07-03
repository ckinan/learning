package javaservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	private String user;
	private String password;

	public void init() {
		System.out.println("LoginServlet.init()");
		user = getServletConfig().getInitParameter("test_user");
		password = getServletConfig().getInitParameter("test_password");
	}

	public void destroy() {
		System.out.println("LoginServlet.destroy()");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("LoginServlet.doGet()");
		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();
		writer.println(
				"<html>\n"
				+ "<head>\n"
				+ "\t<title>Login</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "\t<form action=\"login.htm\" method=\"POST\">\n"
				+ "\t\tUser: <input type=\"text\" name=\"user\"/> <br/>\n"
				+ "\t\tPass: <input type=\"password\" name=\"password\"/> <br/>\n"
				+ "\t\t<input type=\"submit\" value=\"Enter\" /> <br/>\n"
				+ "\t\t(*) In this example, the correct user and password is: admin/1234\n"
				+ "\t</form>\n"
				+ "</body>\n"
				+ "</html>");
		
		writer.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("LoginServlet.doPost()");

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession(true);

		if (request.getParameter("user") != null && request.getParameter("user").equals(user)
				&& request.getParameter("password") != null && request.getParameter("password").equals(password)) {
			session.setAttribute("user", user);
			response.sendRedirect("main.htm");
		} else {
			session.setAttribute("user", null);
			response.sendRedirect("error.htm");
		}
	}

}

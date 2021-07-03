package javaservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ErrorServlet extends HttpServlet {

	public void init() {
		System.out.println("ErrorServlet.init()");
	}

	public void destroy() {
		System.out.println("ErrorServlet.destroy()");
	}

	@SuppressWarnings("rawtypes")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ErrorServlet.doGet()");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == HttpServletResponse.SC_FORBIDDEN) { // Authentication Error

			writer.println(
					"<html>"
					+ "\n<head>"
					+ "\n\t<title>Error</title>"
					+ "\n</head>"
					+ "\n<body>"
					+ "\n\tYou don't have access."
					+ "\n\t<a href=\"http://localhost:8080/java-servlet/login.htm\">Click here to login</a>"
					+ "\n</body>"
					+ "\n</html>");
			
		} else {
			writer.println("<h1>Errors:</h1>");
			Enumeration attributes = request.getAttributeNames();
			while (attributes.hasMoreElements()) {
				String attributeName = (String) attributes.nextElement();
				writer.println(attributeName + ": " + request.getAttribute(attributeName) + "<br/>");
			}
			
			HttpSession session = request.getSession(true);
			Enumeration sessionAttributes = session.getAttributeNames();
			
			writer.println("<h2>Session Attributes</h2>");
			while(sessionAttributes.hasMoreElements()){
				String attributeName = (String) sessionAttributes.nextElement();
				writer.println(attributeName + ": " + session.getAttribute(attributeName) + "<br/>");
			}
			
			writer.println("<h2>Go Back Link</h2>");
			writer.println("<a href=\"http://localhost:8080/java-servlet/main.htm\">Return</A>");
		}
		
		writer.close();
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ErrorServlet.doPost()");
		doGet(request, response);
	}

}

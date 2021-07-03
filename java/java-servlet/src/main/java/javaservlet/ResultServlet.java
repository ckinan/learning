package javaservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ResultServlet extends HttpServlet {

	public void init() {
		System.out.println("ResultServlet.init()");
	}

	public void destroy() {
		System.out.println("ResultServlet.destroy()");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ResultServlet.doGet()");
		
		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();
		writer.println("<h1>I'm MainServlet</h1>");

		Enumeration<String> parameterNames = request.getParameterNames();

		writer.println("<h2>Request parameters</h2>");
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			writer.println(parameterName + ": " + request.getParameter(parameterName) + "<br/>");
		}

		Enumeration<String> headerNames = request.getHeaderNames();

		writer.println("<h2>Request headers</h2>");
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			writer.println(headerName + ": " + request.getHeader(headerName) + "<br/>");
		}

		if (request.getParameter("someText") != null && request.getParameter("someText").equals("error")) {
			throw new NullPointerException("Throwing NullPointerException");
		}

		HttpSession session = request.getSession(true);
		Enumeration sessionAttributes = session.getAttributeNames();

		writer.println("<h2>Session Attributes</h2>");
		while (sessionAttributes.hasMoreElements()) {
			String attributeName = (String) sessionAttributes.nextElement();
			writer.println(attributeName + ": " + session.getAttribute(attributeName) + "<br/>");
		}

		writer.println("<h2>Go Back Link</h2>");
		writer.println("<a href=\"http://localhost:8080/java-servlet/main.htm\">Return</A>");
		
		writer.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ResultServlet.doPost()");
		doGet(request, response);
	}

}

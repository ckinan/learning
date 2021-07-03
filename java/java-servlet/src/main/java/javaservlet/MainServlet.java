package javaservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {

	public void init() {
		System.out.println("MainServlet.init()");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("MainServlet.doGet()");

		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();
		writer.println(
				"<html>\n"
				+ "<body>\n"
				+ "\t<h2>GET Method:</h2>\n"
				+ "\t<form action=\"result.htm\" method=\"GET\">\n"
				+ "\t\tFirst Name: <input type=\"text\" name=\"first_name\"> <br /> Last\n"
				+ "\t\tName: <input type=\"text\" name=\"last_name\" /> <input type=\"submit\"\n"
				+ "\t\t\tvalue=\"Submit\" />\n"
				+ "\t</form>\n\n"
				+ "\t<h2>POST Method: Text Boxes</h2>\n"
				+ "\t<p> (*) Body will be redirected to doGet method to avoid resubmit the request in case of Refresh or Back Browser Button <p/>\n"
				+ "\t<form action=\"main.htm\" method=\"POST\">\n"
				+ "\t\tFirst Name: <input type=\"text\" name=\"first_name\"> <br /> Last\n"
				+ "\t\tName: <input type=\"text\" name=\"last_name\" /> <input type=\"submit\"\n"
				+ "\t\t\tvalue=\"Submit\" />\n"
				+ "\t</form>\n\n"
				+ "\t<h2>POST Method: Checkboxes</h2>\n"
				+ "\t<p> (*) Body will be redirected to doGet method to avoid resubmit the request in case of Refresh or Back Browser Button <p/>\n"
				+ "\t<form action=\"main.htm\" method=\"POST\">\n"
				+ "\t\t<input type=\"checkbox\" name=\"maths\" checked=\"checked\" /> Maths <input\n"
				+ "\t\t\ttype=\"checkbox\" name=\"physics\" /> Physics <input type=\"checkbox\"\n"
				+ "\t\t\tname=\"chemistry\" checked=\"checked\" /> Chemistry <input type=\"submit\"\n"
				+ "\t\t\tvalue=\"Select Subject\" />\n"
				+ "\t</form>\n\n"
				+ "\t<h2>Error Test:</h2>\n"
				+ "\t<p>If you type \"error\", the Servlet will force an Error</p>\n"
				+ "\t<form action=\"main.htm\" method=\"POST\">\n"
				+ "\t\tEnter key word: <input type=\"text\" name=\"someText\"> <input\n"
				+ "\t\t\ttype=\"submit\" value=\"Submit\" />\n"
				+ "\t</form>\n\n"
				+ "\t<h2>Logout:</h2>\n"
				+ "\t<form action=\"logout.htm\" method=\"GET\">\n"
				+ "\t\t<input type=\"submit\" value=\"Logout\" />\n"
				+ "\t</form>\n"
				+ "</body>\n"
				+ "</html>");
		
		writer.close();
	}

	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("MainServlet.doPost()");

		StringBuffer sbLocation = new StringBuffer("/java-servlet/result.htm");
		Enumeration parameterNames = request.getParameterNames();
		int i = 0;
		while (parameterNames.hasMoreElements()) {
			if (i == 0) {
				sbLocation.append("?");
			} else {
				sbLocation.append("&");
			}
			i++;
			
			String currentParameterName = (String) parameterNames.nextElement();
			sbLocation.append(currentParameterName + "=" + request.getParameter(currentParameterName));
		}

		response.sendRedirect(sbLocation.toString());
	}

	public void destroy() {
		System.out.println("MainServlet.destroy()");
	}

}

package javajsp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.HttpJspPage;

public abstract class MySuperClass implements HttpJspPage {

	protected void executeProcess() {
		System.out.println("[From MySuperClass] Process have been executed");
	}

	@Override
	public void jspInit() {
		System.out.println("MySuperClass.jspInit()");
	}

	@Override
	public void _jspService(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("MySuperClass._jspService()");
	}

	@Override
	public void jspDestroy() {
		System.out.println("MySuperClass.jspDestroy()");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("MySuperClass.init()");
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("MySuperClass.init()");
	}
	
	@Override
	public ServletConfig getServletConfig() {
		System.out.println("MySuperClass.getServletConfig()");
		return null;
	}
	
	@Override
	public String getServletInfo() {
		System.out.println("MySuperClass.getServletConfig()");
		return null;
	}
	
	@Override
	public void destroy() {
		System.out.println("MySuperClass.destroy()");
	}
}

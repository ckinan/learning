package javajsp;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyCustomTagAttribute extends SimpleTagSupport {

	private String attribute1;
	private String attribute2;
	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		// Tag Attribute
		JspWriter out = getJspContext().getOut();
		out.println("attribute1: " + attribute1 + ", attribute2: " + attribute2);
		
		// Tag Body
		getJspBody().invoke(sw);
		getJspContext().getOut().println(sw.toString());
	}
	
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

}

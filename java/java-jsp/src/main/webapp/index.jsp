<%@page import="javajsp.MySuperClass"%>
<%@ page import="java.util.Enumeration"%>
<%@ page contentType="text/html"%>
<%-- @page contentType="application/msword" --%>
<%-- @page contentType="application/pdf" --%>
<%@ page errorPage="error.jsp" %>
<%-- <%@ page extends="javajsp.MySuperClass" %> --%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page info="Java JSP Examples" %>
<%@ taglib prefix="mytag" uri="WEB-INF/myCustomTag.tld"%>
<html>
<head>
	<title>Index</title>
</head>
<body>
<h1>Project: java-jsp</h1>
<% System.out.println("[Console] Start JSP Body (Print from scriptlet)"); %>

<%
	Enumeration headerNames = request.getHeaderNames();
	out.println("<h2>Headers</h2>");
	
	while(headerNames.hasMoreElements()){
		String headerName = (String) headerNames.nextElement();
		out.println(headerName + ": " + request.getHeader(headerName) + "<br/>");
	}
	
	out.println("<h2>Implicit Objects</h2>");
	out.println("<p>Implicit Objects: JspWriter out, HttpServletRequest request, HttpServletResponse response and others</p>");
%>

<h2>Statements</h2>
<% for(int i=0; i<5; i++){ %>
	Inside a loop (Loop Statement). Index (Using Scriplet Expression) --> <%= i %> <br/>
<% } %>

<form action="index.jsp" method="GET">
	<h2>Page Directives</h2>
	<table border="1">
	<thead style="background-color: gray;">
		<tr>
			<td>Description</td>
			<td>Action</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Redirect with errorPage directive</td>
			<td><input type="submit" name="btnDirectiveValue" value="errorPage"></td>
		</tr>
		<tr>
			<td>(Not implemented) Execute a method from Super Class</td>
			<td><input type="submit" name="btnDirectiveValue" value="extends" disabled="disabled"></td>
		</tr>
	</tbody>
	</table>
	
	<%
		List<String> myList = new ArrayList<String>();
		for(int i=0; i<5; i++){
			myList.add("My item (" + i + ")");
		}
	%>
	
	<br/>My List (using import java.util.*): <%= myList.toString() %>
</form>

<%
	String btnDirectiveValue = request.getParameter("btnDirectiveValue");
	System.out.println("btnDirectiveValue=" + btnDirectiveValue);
	if("errorPage".equals(btnDirectiveValue)){
		System.out.println("errorPage directive");
		throw new Exception("Forcing errorPage");
	}
// 	else if("extends".equals(btnDirectiveValue)){
// 		executeProcess();
// 	}
%>

<h2>Include Directive</h2>
<%@ include file="external.jsp" %>

<h2>Custom tags</h2>
<h3>Result of mytag:MyCustomTagEmptyBody:</h3>
<mytag:MyCustomTagEmptyBody/><br/>
<h3>Result of mytag:MyCustomTagScriptless:</h3>
<mytag:MyCustomTagScriptless>
	[mytag:MyCustomTagScriptless (the body)]
</mytag:MyCustomTagScriptless><br/>
<h3>Result of mytag:MyCustomTagAttribute:</h3>
<mytag:MyCustomTagAttribute attribute1="value1" attribute2="value2">
	[mytag:MyCustomTagAttribute (the body)]
</mytag:MyCustomTagAttribute>

<h2>Form (POST Method)</h2>
<form action="index.jsp" method="POST">
	First name: <input type="text" name="firstName"/><br/>
	Last name: <input type="text" name="lastName"/><br/>
	<input type="submit" name="btnSend" value="Send"/>
</form>

<%
	// Indicates the action had been submitted
	if("Send".equals(request.getParameter("btnSend"))){
		out.println("<h3>Result after submit form (POST Method)</h3>");
		out.println("First Name: " + request.getParameter("firstName") + "<br/>");
		out.println("Last Name: " + request.getParameter("lastName"));
	}
%>

<% System.out.println("[Console] End JSP Body (Print from scriptlet)"); %>
</body>
</html>
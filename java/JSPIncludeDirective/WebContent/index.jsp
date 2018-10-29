<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Welcome to the World Manager</title>
	</head>
	<body>
		<!-- Include using a Directive -->
		<%@ include file = "header.jsp" %>
		<table style="width:100%;">
			<tr>
				<td style="width:25%;height:80%;" valign="top">
					<%@include file = "navbar.jsp" %>
				</td>
				<td  style="width:75%;height:80%;" valign="top">
					<h1>content goes here</h1>
     				<p>But if we did, this page would render it here<br>
     				     Server Version: <%= application.getServerInfo() %><br>
                         Servlet Version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %><br>
                         JSP Version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><br>
					   <br>
					   Hello <strong>${initParam.dbuserid}</strong>
					</p>
				</td>
			</tr>
		</table>
		<!-- Include using JSP standard action -->
		<jsp:include page="footer.jsp"></jsp:include>
		<%-- This comment does not render --%>
	</body>
</html>
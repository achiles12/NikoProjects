<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!--     global error handling must be set on web.xml -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Welcome to the World Manager</title>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<table style="width:100%;">
			<tr>
				<td style="width:25%;height:80%;" valign="top">
					<%@ include file="navbar.jsp" %>
				</td>
				<td style="width:75%;height:80%;">
				
					<h1>From Action Not Allowed.</h1> <br>
					<h3>Something has gone wrong with the system.
					The incident has been logged for research purposes. 
					Please try again.</h3>
					<p>${pageContext.exception.message}</p>
				</td>
			</tr>
		</table>
		<jsp:include page="footer.jsp" />
	</body>
</html>
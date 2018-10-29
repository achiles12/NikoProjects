<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    errorPage="errorHandler.jsp" %> <!-- will call the error handler page -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bad Page</title>
	</head>
	<body>
		<% 
			if (request.getParameter("notSetYet") == null)
			{
				// this will throw a custom error message
				throw new IllegalArgumentException("Not Set Yet is " 
					+ " Why do I have to put up with this");
			}
		%>
	</body>
</html>
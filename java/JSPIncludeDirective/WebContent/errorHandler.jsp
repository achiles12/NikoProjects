<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isErrorPage="true" %>
    
<!--     this is to be called whenever there is an error on a page -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Our bad!</title>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<table style="width:100%;">
			<tr>
				<td style="width:25%;height:80%;" valign="top">
					<%@ include file="navbar.jsp" %>
				</td>
				<td style="width:75%;height:80%;">
					<h1>From Error Handler</h1><br>
					<h3>An Error has occurred!</h3><br>
					<%-- <% 
					this is a page directive error handling message may not work with global exception handling
						/* output the error onto the page */
						out.println(exception.getMessage());
					%> --%>
					
					<!-- this is an expression language error handling message works better with global exception handling -->
					${pageContext.exception.message}
				</td>
			</tr>
		</table>
		<jsp:include page="footer.jsp" />
	</body>
</html>
<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isELIgnored="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ignore EL True</title>
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<table style="width:100%;">
			<tr>
				<td style="width:25%;height:80%;" valign="top">
					<%@ include file="navbar.jsp" %>
				</td>
				<td style="width:75%;height:80%;">
					<h1>We don't know EL yet!</h1>
					<p>But if we did, this page would render it here<br>
					   <br>
					   Hello <strong>${initParam.dbuserid}</strong>
					</p>
				</td>
			</tr>
		</table>
		<jsp:include page="footer.jsp" />
	</body>
</html>
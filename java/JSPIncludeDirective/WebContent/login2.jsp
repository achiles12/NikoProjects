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
		<%!
		Cookie[] MyCookies;
		String uid = "";
		String pwd = "";
		int authLevel = 0;
		%>
		<%
			//get uid/pwd from cookie/session
			//HttpSession s = request.getSession();
			if (session.getAttribute("uid") == null || session.getAttribute("uid").equals("")) {
				//try to use cookies
				MyCookies = request.getCookies();
				if (MyCookies != null) {
					for (Cookie c : MyCookies) {
						if (c.getName().equalsIgnoreCase("credentials_uid")) {
							//set the uid from cookie value
							uid = c.getValue();
						} else if (c.getName().equalsIgnoreCase("credentials_pwd")) {
							//set the pwd from cookie value
							pwd = c.getValue();
						}
					}
				}
			} else {
				//use sessions
				uid = (String)session.getAttribute("uid");
				pwd = (String)session.getAttribute("pwd");
			}
		%>
		<table style="width:100%;">
			<tr>
				<td style="width:25%;height:80%;" valign="top">
					<%@include file = "navbar.jsp" %>
				</td>
				<td  style="width:75%;height:80%;" valign="top">
					<form id="form1" method="post" action="LoginUserTwo">
						<table style="width:450px;">
							<tr>
								<td>
									<span>UserName:</span>
								</td>
								<td>
									<input name="uid" type="text" style="width:250px;" value="<%=uid%>" />
								</td>
							</tr>
							<tr>
								<td>
									<span>Password:</span>
								</td>
								<td>
									<input name="pwd" type="password" style="width:250px;" value="<%=pwd%>" />
								</td>
							</tr>
							<tr>
								<td colspan="2" align="right">
									<input name="rememberMe" type="checkbox">&nbsp;Remember Me
								</td>
							</tr>
							<tr>
								<td colspan="2" align="right">
									<input type="submit" value="Sign In" style="width:250px;" />
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		<!-- Include using JSP standard action -->
		<jsp:include page="footer.jsp"></jsp:include>
		<%-- This comment does not render --%>
	</body>
</html>
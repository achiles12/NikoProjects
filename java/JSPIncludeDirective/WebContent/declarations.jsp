<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Use of Declarations</title>
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
				    <p>
					<%! /* this is for declarations only <%! */
					Calendar gc = GregorianCalendar.getInstance(); 
					String hour = String.format("%s", gc.get(Calendar.HOUR));
					String min = String.format("%s", gc.get(Calendar.MINUTE));
					String sec = String.format("%s", gc.get(Calendar.SECOND));
					Random r = new Random(gc.getTimeInMillis());
				%>
				<%  /* this is for actual java code */
					if (hour.length() < 2) hour = String.format("0%s", hour);
					if (min.length() < 2) min = String.format("0%s", min);
					if (sec.length() < 2) sec = String.format("0%s", sec);
				
					out.println("Current Time: <h1>" + hour + ":" + min + ":" + sec + "</h1>");
					out.println("Your lucky number for today: <h2>" + r.nextInt() + "</h2>");
				%>
					</p>
				</td>
			</tr>
		</table>
		<!-- Include using JSP standard action -->
		<jsp:include page="footer.jsp"></jsp:include>
		<%-- This comment does not render --%>
	</body>
</html>
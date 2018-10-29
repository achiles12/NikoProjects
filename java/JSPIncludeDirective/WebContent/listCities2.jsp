<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    
    import = "company.citymanagerweb.model.*,
    		  company.citymanager.helpers.*,
    		  java.sql.*,
    		  java.io.*"
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>List All Cities with Login</title>
	</head>
	<body>
		<!-- Include using a Directive -->
		<%@include file = "header.jsp" %>
		<%!
		String uid = "";
		String pwd = "";
		DBManager dbm;
	    %>
	    <%
		
	    //make sure user is logged in
		//HttpSession s = request.getSession();
		//direct access to HttpSession --> session!!
		if (session.getAttribute("authlevel") == null || session.getAttribute("authlevel").equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("login2.jsp");
			rd.forward(request, response);
		} else {
			Integer authLevel = (Integer)session.getAttribute("authlevel");
			if (authLevel < 1) {
				RequestDispatcher rd = request.getRequestDispatcher("login2.jsp");
				rd.forward(request, response);
			}
		}
		
		// get uid if session attribute is not null
		if (session.getAttribute("uid") != null && !session.getAttribute("uid").equals("")) {
			uid = (String)session.getAttribute("uid");
		}
		
		// get password if session attribute is not null
		if (session.getAttribute("pwd") != null && !session.getAttribute("pwd").equals("")) {
			pwd = (String)session.getAttribute("pwd");
		}
		%>
		<%
			if (uid != null && !uid.equals("")) {
				%>
					<h1>Welcome Back <%= uid %></h1>
				<% 
			}
		%>
		<table style="width:100%;">
			<tr>
				<td style="width:25%;height:80%;" valign="top">
					<%@include file = "navbar.jsp" %>
				</td>
				<td style="width:75%;height:80%;" valign="top">
					We don't know how to use expressions yet so we'll do it all here
					<%-- We don't know how to use expressions yet so we'll do it all here --%>
					<% 
					
						//generate the output in a StringBuilder
						//java.lang not needed at import.
						System.out.println("List all cities JSP"); 
						
						StringBuilder sb = new StringBuilder("<html><body>");
						
						//get the DBManager from context
						//DBManager dbm = (DBManager)getServletContext().getAttribute("WorldDBManager");
						//application --> getServletContext()
						System.out.println("before db manager instantiation");
						dbm = (DBManager) application.getAttribute("WorldDBManager");
						System.out.println("after db manager instantiation");	
						
						try { System.out.println("try to connect");
						//connect to the db and open the connection
							if (!dbm.isConnected()) {
								if (!dbm.openConnection()) {
									//massive failure, log it
									sb.append("Could not connect to the database...");
								}
							}
							System.out.println("before result set");
							//ID NAME CountryCode District Population
							//get the cities into a table:
							sb.append("<table border=1>" 
										+ "<tr><td>ID</td><td>NAME</td><td>COUNTRY_CODE</td>"
										+ "<td>DISTRICT</td><td>POPULATION</td></tr>");
						
							//is this MVC? NO.  Queries should be logic outside of the controller.
							//views should handle the results.  We clearly have a way to go to
							//get to MVC from here.
							// comment out and get the query from the DBWorldQueries class
							//String query = "select * from city where CountryCode = 'PHL'" +
							//				" order by 4,2";
						
							
							//TRUE MVC ERROR: the query should NOT be on the JSP page...
							String query = DBWorldQueries.getCitiesByDistrictByPopulation();
							
							//TRUE MVC ERROR: neither should creation of the result set...
							ResultSet rs = dbm.ExecuteResultSet(query);
							
							while (rs.next()) {
								int id = rs.getInt("ID");
								String name = rs.getString("NAME");
								String ctry = rs.getString("CountryCode"); 
								String dist = rs.getString("District"); 
								int pop = rs.getInt("Population");
								
								sb.append("<tr><td>" + id + "</td>" 
										+ "<td>" + name + "</td>" 
										+ "<td>" + ctry + "</td>" +
										"<td>" + dist + "</td>" +
										"<td>" + pop + "</td></tr>");
							}
							sb.append("</table>");
						} catch(Exception e) {
							sb.append("<h1>ERROR: " + e.getMessage() + "</h1>");
						}
						
						sb.append("</body></html>");
					
						//don't need to get the writer anymore...
						//response.setContentType("text/html");
						//PrintWriter out = response.getWriter();
						
						out.println(sb);
						
						System.out.println("end of JSP scriplet");
						
					%>
				</td>
			</tr>
		</table>
		<!-- Include using JSP standard action -->
		<jsp:include page="footer.jsp"></jsp:include>
		<%-- This comment does not render --%>
	</body>
</html>
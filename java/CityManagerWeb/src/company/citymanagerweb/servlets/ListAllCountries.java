package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.citymanager.helpers.DBWorldQueries;
import company.citymanagerweb.model.DBManager;

/**
 * Servlet implementation class ListAllCountries
 */
@WebServlet("/ListAllCountries")
public class ListAllCountries extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllCountries() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//get the DBManager from context
		DBManager dbm = (DBManager)getServletContext().getAttribute("WorldDBManager");
				
		//generate the output in a StringBuilder
		StringBuilder sb = new StringBuilder("<html><body>");
		
		try {
			//connect to the db and open the connection
			if (!dbm.isConnected()) {
				if (!dbm.openConnection()) {
					//massive failure, log it
					sb.append("Could not connect to the database...");
				}
			}
			
			//Code Name Population
			//get the cities into a table:
			sb.append("<table border=1>" 
						+ "<tr><td>CODE</td><td>NAME</td><td>POPULATION</td></tr>");
			
			String query = DBWorldQueries.getCountriesByName();
			ResultSet rs = dbm.ExecuteResultSet(query);
			while (rs.next()) {
				String id = rs.getString("CODE");
				String name = rs.getString("NAME");
				int pop = rs.getInt("POPULATION");
				
				sb.append("<tr><td>" + id + "</td>" 
						+ "<td>" + name + "</td>" 
						+ "<td>" + pop + "</td></tr>");
			}
			sb.append("</table>");
		} catch(Exception e) {
			sb.append("<h1>ERROR: " + e.getMessage() + "</h1>");
		}
		
		sb.append("</body></html>");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(sb);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

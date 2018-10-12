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
import company.citymanagerweb.model.MySQLServerConnectionBehavior;
import company.citymanagerweb.model.ServerConnectionBehavior;

/**
 * Servlet implementation class ListAllCities
 */
@WebServlet("/ListAllCities")
public class ListAllCities extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllCities() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		//get db con info from context init params
		// comment out because we will get this from context attribute using the DBManagerSetup class
/*		String uid = getServletContext().getInitParameter("dbuserid");
		String pwd = getServletContext().getInitParameter("dbuserpwd");
		String cat = getServletContext().getInitParameter("dbinitcat");*/

		//generate the output in a StringBuilder
		StringBuilder sb = new StringBuilder("<html><body>");
		
		//set the scb for mySQL
		// comment out because we will get this from context attribute using the DBManagerSetup class
/*		ServerConnectionBehavior scb = new MySQLServerConnectionBehavior(uid, pwd, cat);
		System.out.println(scb.getConnectionDetails());
		System.out.println(scb.getConnectionURL());*/
		
		
		//create the manager
		// comment out because we will get this from context attribute
		//DBManager dbm = new DBManager(scb);
		
		// get the DBManager from context
		System.out.println("before db manager instantiation");
		DBManager dbm = (DBManager)getServletContext().getAttribute("WorldDBManager");
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
			
			String query = DBWorldQueries.getCitiesByDistrictByPopulation();
			
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
		System.out.println("before printwriter");
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

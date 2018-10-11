package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenuResponderHelper
 */
@WebServlet("/mainmenuresponderhelper.do")
public class MainMenuResponderHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuResponderHelper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Helper doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("Helper doPost");
		
		String useDB = (String) request.getAttribute("useDB"); 
		String sendEmail = (String) request.getAttribute("sendEmail");
		String userChoice = (String) request.getAttribute("userChoice");
		
		// this is just to output the attributes
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String pageToNavigate = "";
		
		/*
		 * 	<option id="1" value="1">
							List Cities
						</option>
						<option id="2" value="2">
							Add a new city
						</option>
						<option id="3" value="3">
							Delete a city
						</option>
		 * */
		
		if (userChoice.equals("1")) {
			pageToNavigate = "List Cities";
		} else if (userChoice.equals("2")) {
			pageToNavigate = "Add City";
		} else if (userChoice.equals("3")) {
			pageToNavigate = "Delete City";
		}
		
		// if sendEmail is null then set to false else set to true
		boolean optEmail = (sendEmail == null ? false : true); //sendEmail.equals("1"));
		// if optDB is null then set to false else set to true
		boolean optDB = (useDB == null ? false : useDB.equals("1"));
		
		String message = String.format("<span>You chose to show the page to %s" // %s -> String use for first argument pageToNavigate
				+ " and set useDB to %b" // %b -> Boolean use for second argument optDB
				+ " and set sendEmail to %b" // %b -> Boolean use for Third argument optEmail
				+ "</span>"
				, pageToNavigate
				, optDB
				, optEmail
				);
		
		out.println("<html><body><h1>"
				+ message
				+ "</h1></body></html>");
	
	}

}

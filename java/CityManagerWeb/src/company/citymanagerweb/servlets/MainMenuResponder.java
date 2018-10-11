package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenuResponder
 */
@WebServlet("/mainmenuresponder.do")
public class MainMenuResponder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuResponder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		
		// get the parameter that was posted
		// get parameter value from list
		String userChoice = request.getParameter("menuChoice");
		System.out.println("userChoice : " + userChoice);

		// get parameter value from checkbox
		String[] userOptions = request.getParameterValues("adminoptions");
		
		if (userOptions != null) {
			for (String userOption : userOptions) {
				System.out.println("userOptions: " + userOption);
			};
		};
		
		// create a query string from the parameters
		// this could be used for other parameters but this logic is helpful for parameter arrays
		
		StringBuilder params = new StringBuilder("");
		String queryStringParams = "";
		
		if (userOptions != null) {
			
			boolean isFirst = true;
			for (int i = 0; i < userOptions.length; i++){
				if(isFirst) {
					params.append("?"); // to mark end of the URL and start of the first parameter
				} else {
					params.append("&"); // to mark subsequent parameters
				};
				
				if (userOptions[i].equalsIgnoreCase("useDB")) {
					params.append("useDB=1");
				} else if (userOptions[i].equalsIgnoreCase("sendEmail")) {
					params.append("sendEmail=1");
				};
				
				isFirst = false;
			};
			queryStringParams = params.toString();
			
			System.out.println("queryStringParams : " + queryStringParams);
		};
		
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
			response.sendRedirect("ListCities.html" + queryStringParams);
			
		}else if (userChoice.equals("2")) {
			response.sendRedirect("AddCity.html" + queryStringParams);
			
		}else if (userChoice.equals("3")) {
			response.sendRedirect("DeleteCity.html" + queryStringParams);
			
		}else { //invalid response
			response.sendRedirect("index.html");
		};
		
		
		
		
		
	}

}

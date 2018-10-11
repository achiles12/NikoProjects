package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenuResponder
 */
@WebServlet("/mainmenuresponder2.do")
public class MainMenuResponder2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuResponder2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Responder2 doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Responder2 doPost");
		
		// get the parameter that was posted
		// get parameter value from list
		String userChoice = request.getParameter("menuChoice");
		System.out.println("userChoice : " + userChoice);

		// use of attributes sends parameters without showing them in the query string
		
		// set parameter to a system attribute
		request.setAttribute("userChoice", userChoice);
		System.out.println("Attribute userChoice : " + request.getAttribute("userChoice"));


		// get parameter value from checkbox
		String[] userOptions = request.getParameterValues("adminoptions");
		
		if (userOptions != null) {
			for (String userOption : userOptions) {
				System.out.println("userOptions: " + userOption);
			};
		};
		
		// this could be used for other parameters but this logic is helpful for parameter arrays
			
		// set parameter to a system attribute
		if (userOptions != null) {
			
			for (int i = 0; i < userOptions.length; i++){
			
				if (userOptions[i].equalsIgnoreCase("useDB")) {
					request.setAttribute("useDB","1");
					System.out.println("Attribute useDB : " + request.getAttribute("useDB"));
					
				} else if (userOptions[i].equalsIgnoreCase("sendEmail")) {
					request.setAttribute("sendEmail","1");
					System.out.println("Attribute sendEmail : " + request.getAttribute("sendEmail"));
				}
			}
		}
		
		/*
		 * attributes vs parameters
		 * can send any object in an attribute not just a string
		 * with a matching value as a string
		 * 
		 * HTML files cannot read attributes
		 * must use an intermediary servlet
		 * (JSPs however can access java code like attributes #TBD)
		*/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mainmenuresponderhelper.do");
		dispatcher.forward(request, response);
		
	}

}

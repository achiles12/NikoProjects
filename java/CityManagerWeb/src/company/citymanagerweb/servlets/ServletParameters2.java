package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletParameters2
 */
@WebServlet("/ServletParameters2")
public class ServletParameters2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletParameters2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet");
		
		// get servlet parameter
		String secretCode = getServletConfig().getInitParameter("secretCode");

		// get context parameter
		String dbURL = getServletContext().getInitParameter("dbURL");
		
		//spit out the information
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String outString = String.format("Secret Code: %s\tDB URL: %s\n"
							, (secretCode == null ? "unauthorized" : secretCode)
							, dbURL);
		
		out.println("<html><body><h3>" + outString + "</h3></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		//doGet(request, response);
	}

}

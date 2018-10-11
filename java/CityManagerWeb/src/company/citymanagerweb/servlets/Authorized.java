package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Authorized
 */
@WebServlet("/authorized.do")
public class Authorized extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authorized() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		boolean authorized = false;
		String uid = null;
		int authLevel = -1;
		if (s.getAttribute("userName") != null)
		{
			uid = (String)s.getAttribute("userName");
		}
		if (s.getAttribute("userAuthLevel") != null)
		{
			authLevel = (int)s.getAttribute("userAuthLevel");
		}
		
		if (authLevel >= 1 && uid != null && !uid.equals(""))
		{
			authorized = true;
		}
		
		if (!authorized)
		{
			response.sendRedirect("Login.html");
			
			/* use this if cookies are disabled; session id is displayed on the URL bar
		  	response.sendRedirect(
		 		response.encodeRedirectURL("Login.html"));
			*/
		}
		else
		{
			response.sendRedirect("/CityManagerWeb/destinationpage.do");
			
			/* use this if cookies are disabled; session id is displayed on the URL bar
		  	response.sendRedirect(
		 		response.encodeRedirectURL("/CityManagerWeb/destinationpage.do"));
			 */
		}
	}
}

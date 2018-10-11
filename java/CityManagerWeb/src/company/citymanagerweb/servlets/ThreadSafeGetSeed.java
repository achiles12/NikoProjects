package company.citymanagerweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreadSafeGetSeed
 */
@WebServlet("/threadsafegetseed.do")
public class ThreadSafeGetSeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadSafeGetSeed() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve the form parameter user selection
		String doThreadSafe = request.getParameter("threadsafetyselection");
		//retrieve the context parameter for initial seed
		int initialSeed = Integer.parseInt(getServletContext().getInitParameter("initialseed"));
		
		//seed the servlet context attribute for current seed
		// set attribute at the application level
		getServletContext().setAttribute("currentSeedValue", initialSeed);
		
		//determine where to direct traffic
		if (doThreadSafe.equalsIgnoreCase("nonthreadsafe"))
		{
			response.sendRedirect("nonthreadsafeservlet.do");
			
			/* use this if cookies are disabled; session id is displayed on the URL bar
		  	response.sendRedirect(
		 		response.encodeRedirectURL("Login.html"));
			*/
		}
		else if (doThreadSafe.equalsIgnoreCase("threadsafe"))
		{
			response.sendRedirect("threadsafeservlet.do");
		}
		else if (doThreadSafe.equalsIgnoreCase("requestthread"))
		{
			response.sendRedirect("threadsaferequest.do");
		}
		else
		{
			response.sendRedirect("index3.html");
		}
	}
}

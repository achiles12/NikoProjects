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
 * Servlet implementation class DestinationPage
 */
@WebServlet("/destinationpage.do")
public class DestinationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession s = request.getSession();
		System.out.println(s.getAttribute("userName"));
		System.out.println(s.getAttribute("userAuthLevel"));
		
		
		if (s != null && !s.isNew() && s.getAttribute("userName") != null)
		{
			String uid = (String)s.getAttribute("userName");
			out.println("<html><body><span>Welcome<span>&nbsp;<strong>"
					+ uid + "</strong></body></html>");
		}
		else
		{
			out.println("<html><body><span>Unauthorized<span>"
					+ "</body></html>");
		}
	}

	

}

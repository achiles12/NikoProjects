package company.citymanager.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginUser
 */
@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		
		HttpSession s = request.getSession();
		s.setAttribute("uid", uid);
		s.setAttribute("pwd", pwd);
		
		if (uid == "" || pwd == "") {
			throw new IllegalArgumentException("Invalid Username or Password");
		}
		
		String destination = "showUserCredentials.jsp";
		if (request.getParameter("noSession") != null)
		{
			String noSession = request.getParameter("noSession");
			if (noSession.equalsIgnoreCase("ON"))
			{
				destination = "showUserCredentials2.jsp";
			}
		}
		
		//if use request dispatcher place a / in front of url
		//RequestDispatcher rd = request.getRequestDispatcher("/" + destination);
		//rd.forward(request, response);
		response.sendRedirect(response.encodeRedirectURL(destination));
	}

}

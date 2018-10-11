package company.citymanagerweb.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NonThreadSafeServlet
 */
@WebServlet("/nonthreadsafeservlet.do")
public class NonThreadSafeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NonThreadSafeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the attribute and multiply by two to increase 
		//factor but pretend this is a longer running process...
		StringBuilder msg = new StringBuilder("");
		msg.append("<html><body>");
		int currentSeed = (int)getServletContext().getAttribute("currentSeedValue");
		msg.append(String.format("Attribute Retrieved Value: %d<br>", currentSeed));
		currentSeed *= 2;
		//set the value
		getServletContext().setAttribute("currentSeedValue", currentSeed);
		try {
			Thread.sleep(5000);  //wait 5 seconds...
		} catch (InterruptedException e) {
			//do nothing...
		}
		//re-fetch the value after waiting...
		currentSeed = (int)getServletContext().getAttribute("currentSeedValue");
		msg.append(String.format("Attribute retrieved value after process: %d<br>"
								, currentSeed));
		
		msg.append("</body></html>");
		
		//spit it out
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(msg);
	}
}

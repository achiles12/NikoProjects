package com.nikoprojects.helloworld;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;  

/**
 * Servlet implementation class HelloWorld
 */
// Original mapping of Servlet url generated automatically
//@WebServlet(description = "Hello World", urlPatterns = { "/HelloWorld" })

//customer mapping aside from WebContent -> web.xml
@WebServlet(description = "Hello World", urlPatterns = { "/HiddenServlets/HelloWorld2.do" })
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		
		double sqrtval = Math.sqrt(4.0);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    System.out.println(formatter.format(date));
		
		out.println("<html><body>"
				+ "<h1 align='center'>Hello World</h1>"
				+ "<br>The squareroot of 4.0 is " + sqrtval + "</br>"
				+ "<br> the date and time is " + date + "</br>"
				+ "</body></html>");
		
		
		
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

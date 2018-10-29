package company.citymanager.helpers;

import javax.servlet.*;

import company.citymanagerweb.model.DBManager;
import company.citymanagerweb.model.MySQLServerConnectionBehavior;
import company.citymanagerweb.model.ServerConnectionBehavior;

public class DBManagerSetup implements ServletContextListener {

	// called and executed by listener-class on web.xml file
	
	private DBManager dbm = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
		
		//cleanup the connection when the context is destroyed
				if (dbm != null) {
					if (dbm.isConnected()) {
						dbm.closeConnection(false);
					}
				}
				dbm = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextInitialized(sce);
		
		//access the servlet context from the event argument (renamed sce)
		//get db con info from context init params
		ServletContext sc = sce.getServletContext();
		String uid = sc.getInitParameter("dbuserid");
		String pwd = sc.getInitParameter("dbuserpwd");
		String cat = sc.getInitParameter("dbinitcat");
	
		//set the scb for mySQL
		ServerConnectionBehavior scb = new MySQLServerConnectionBehavior(uid, pwd, cat);
		System.out.println("DB Manager Setup: " + scb.getConnectionDetails());
		System.out.println("DB Manager Setup: " + scb.getConnectionURL());
		
		//create the manager
		dbm = new DBManager(scb);
		
		//put the manager into the servlet context attributes for global use in
		//the application
		sc.setAttribute("WorldDBManager", dbm);
		
		System.out.println("WorldDBManager created and added to context");
	}

}

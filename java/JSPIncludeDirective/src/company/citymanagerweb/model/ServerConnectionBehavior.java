package company.citymanagerweb.model;

import java.sql.Connection;

/**
 *	ServerConnectionBehavior allows polymorphic database connections. 
 *
 */

public interface ServerConnectionBehavior {
	
	/*these are method declarations not variable declarations*/
	
	Connection getConnection(); // main method to get the connection and work with it
	String getConnectionURL(); // connection parameters in order to make a new parameter
	String getConnectionDetails(); // tells what is happening with the connection
	String getTablesSchemaQuery(); // check schema tables to see if we are connected

}

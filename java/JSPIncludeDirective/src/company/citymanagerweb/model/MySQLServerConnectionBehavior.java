package company.citymanagerweb.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLServerConnectionBehavior extends DBUserInfo implements ServerConnectionBehavior {

	public MySQLServerConnectionBehavior() {
		super();
	}

	public MySQLServerConnectionBehavior(String uid, String pwd, String cat) {
		super(uid, pwd, cat);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection cn = DriverManager.getConnection(getConnectionURL());
			return cn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getConnectionURL() {
		return String.format("jdbc:mysql://localhost/%s" // %s string from first argument
				+ "?user=%s" // %s string from second argument
				+ "&password=%s" // %s string from third argument
				, getCatalog(), getUserID(), getPassword());
	}

	@Override
	public String getConnectionDetails() {
		return "MySQL Database Connection to " + getCatalog();
	}

	@Override
	public String getTablesSchemaQuery() {
		return "select table_name from information_schema.tables " + "where table_schema = " + getCatalog();
	}

}

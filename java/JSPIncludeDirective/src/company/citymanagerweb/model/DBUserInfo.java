package company.citymanagerweb.model;

public abstract class DBUserInfo {

	private String uid;
	private String pwd;
	private String cat;
	

	public DBUserInfo() {
		
	}
	public DBUserInfo(String userID, String password, String catalog) {
		this.uid = userID;
		this.pwd = password;
		this.cat = catalog;
	}
	public String getUserID() {
		return uid;
	}
	public void setUserID(String uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return pwd;
	}
	public void setPassword(String pwd) {
		this.pwd = pwd;
	}
	public String getCatalog() {
		return cat;
	}
	public void setCatalog(String cat) {
		this.cat = cat;
	}
	
	
}

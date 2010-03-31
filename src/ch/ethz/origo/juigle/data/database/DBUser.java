package ch.ethz.origo.juigle.data.database;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (3/31/2010)
 * @since 0.1.0 (3/31/2010)
 * 
 */
public class DBUser {
	
	private String userName;
	private String password;
	
	public DBUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

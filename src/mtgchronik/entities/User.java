package mtgchronik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="UserTable")
public class User extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1398208222167697803L;

	@Column(unique=true)
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private boolean admin;
	
	public User(){}
	
	public User(String userName, String password, boolean admin){
		this.userName=userName;
		this.password=password;
		this.admin=admin;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}

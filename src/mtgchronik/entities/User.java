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
	private String role;
	
	public User(){}
	
	public User(String userName, String password, String role){
		this.userName=userName;
		this.password=password;
		this.role=role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}

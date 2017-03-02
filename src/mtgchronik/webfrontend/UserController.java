package mtgchronik.webfrontend;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import mtgchronik.bcrypt.BCrypt;
import mtgchronik.entities.User;
import mtgchronik.services.UserService;

@SessionScoped
@Named
public class UserController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1708645057300799867L;

	@Inject
	private UserService userService;
	
	private Logger logger;
	private User currentUser;
	private String requestedPage;
	private String userName;
	private String password;
	
	private Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger(getClass());
		return logger;
	}
	

	public String getRequestedPage() {
		return requestedPage;
	}

	public void setRequestedPage(String requestedPage) {
		this.requestedPage = requestedPage;
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

	public String login() {
		//TODO: Get the correct persistence unit from the request
		currentUser = userService.getUserByName(userName);
		if (!BCrypt.check(password,currentUser.getPassword())){
			getLogger().info("Login of user " + currentUser.getUserName() + " not successful!");
			currentUser=null;
		}

		if (currentUser == null) {
			// Error. User not found or wrong credentials
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown login", "Try again!"));

			userName = null;
			password = null;
			return "/error.xhtml";
		} else {
			getLogger().info("Login of user " + currentUser.getUserName() + " successful.");
			if (requestedPage != null) {
				return requestedPage;
			} 
				
			return "/administration/administration.xhtml";
		}
	}

	public String logout() {
		currentUser=null;
		userName = null;
		password = null;
		requestedPage=null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml";
	}

	public User getCurrentUser() {
		return currentUser;
	}

	/*
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}*/
	
	public boolean isLoggedIn(){
		return currentUser!=null;
	}

}

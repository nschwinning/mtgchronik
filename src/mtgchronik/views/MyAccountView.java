package mtgchronik.views;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mtgchronik.bcrypt.BCrypt;
import mtgchronik.entities.User;

@ManagedBean
@ViewScoped
public class MyAccountView extends AbstractView{

	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm;
	
	
	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	public void saveNewPassword(){
		User user = getCurrentUser();
		if (BCrypt.check(oldPassword, user.getPassword())){
			if (newPassword.equals(newPasswordConfirm)){
				user.setPassword(BCrypt.hash(newPassword));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Passwort erfolgreich gesetzt."));
			}
		}
		else{
			
		}
		
	}
}

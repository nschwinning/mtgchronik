package mtgchronik.webfrontend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import mtgchronik.services.UserService;

@Startup
@Singleton
public class UserManagementBean {
	
	@Inject
	private UserService userService;

	@PostConstruct
	public void init(){
		if (userService.getUserByName("mtgadmin")==null){
			try {
				userService.createUser("mtgchronik", hashPassword("mtgchronik"), "ADMINISTRATOR");
				System.out.println("Admin mtgchronik successfully created!");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();
        for(byte b1 : b){
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }
        return sb.toString();
    }
}

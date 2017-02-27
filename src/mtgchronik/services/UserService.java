package mtgchronik.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mtgchronik.entities.User;

@Stateless
public class UserService {
	
	@PersistenceContext(name="mtgchronik")
	private EntityManager em;
	
	public User getUserByName(String userName){
		TypedQuery<User> q = em.createQuery("FROM UserTable WHERE userName=:userName", User.class);
		q.setParameter("userName", userName);
		try{
			return q.getSingleResult();
		}
		catch (NoResultException e){
			return null;
		}
	}
	
	public User createUser(String userName, String password, String role){
		User user = new User(userName,password,role);
		em.persist(user);
		return user;
	}

}

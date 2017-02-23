package mtgchronik.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mtgchronik.entities.Player;

@Stateless
public class PlayerService {
	
	@PersistenceContext(name="mtgchronik")
	private EntityManager em;
	
	public List<Player> getAllPlayers(){
		TypedQuery<Player> q = em.createQuery("FROM Player", Player.class);
		return q.getResultList();
	}
	
	public Player createPlayer(String firstName, String lastName){
		Player player = new Player(firstName,lastName);
		em.persist(player);
		return player;
	}
	
	public Player getPlayerByName(String firstName,String lastName){
		TypedQuery<Player> q = em.createQuery("FROM Player WHERE firstName=:firstName AND lastName=:lastName", Player.class);
		q.setParameter("firstName", firstName);
		q.setParameter("lastName", lastName);
		try{
			return q.getSingleResult();
		}
		catch (NoResultException e){
			return null;
		}
	}

}

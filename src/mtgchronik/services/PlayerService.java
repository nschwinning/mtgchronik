package mtgchronik.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mtgchronik.entities.LineUp;
import mtgchronik.entities.Player;
import mtgchronik.entities.PlayerInstance;

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
	
	public PlayerInstance createPlayerInstance(LineUp lineUp,int position){
		PlayerInstance playerInstance = new PlayerInstance(lineUp, position);
		em.persist(playerInstance);
		return playerInstance;
	}
	
	public PlayerInstance updatePlayerInstance(PlayerInstance playerInstance){
		if (playerInstance.getId()==0){
			em.persist(playerInstance);
			return playerInstance;
		}
		return em.merge(playerInstance);
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
	
	public List<PlayerInstance> getPlayerInstancesForLineUp(LineUp lineUp){
		TypedQuery<PlayerInstance> q = em.createQuery("FROM PlayerInstance WHERE lineUp=:lineUp", PlayerInstance.class);
		q.setParameter("lineUp", lineUp);
		return q.getResultList();
	}

}

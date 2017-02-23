package mtgchronik.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PlayerService {
	
	@PersistenceContext(name="mtgchronik")
	private EntityManager em;

}

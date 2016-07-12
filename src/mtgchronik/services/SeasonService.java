package mtgchronik.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mtgchronik.entities.Season;

@Stateless
public class SeasonService {
	
	@PersistenceContext(name="mtgchronik")
	private EntityManager em;
	
	public Season createSeason(int start){
		Season season = new Season(start);
		em.persist(season);
		return season;
	}
	
	public Season getSeasonById(long id){
		TypedQuery<Season> q = em.createQuery("FROM Season where id=:id", Season.class);
		q.setParameter("id", id);
		try{
			return q.getSingleResult();
		}
		catch (NoResultException nre){
			return null;
		}
	}
	
	public List<Season> getAllSeasons(){
		Query q = em.createQuery("FROM Season ORDER BY startYear");
		List<Season> seasonList = q.getResultList();
		return seasonList;
	}
	
}

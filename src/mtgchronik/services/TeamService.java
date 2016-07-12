package mtgchronik.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mtgchronik.entities.Season;
import mtgchronik.entities.Team;
import mtgchronik.entities.TeamInstance;

@Stateless
public class TeamService {

	@PersistenceContext(name="mtgchronik")
	private EntityManager em;
	
	public Team createTeam(String name){
		Team team = new Team(name);
		em.persist(team);
		return team;
	}
	
	public TeamInstance createTeamInstance(Team team, Season season){
		TeamInstance teamInstance = new TeamInstance(team, season);
		em.persist(teamInstance);
		return teamInstance;
	}
	
	public List<Team> getAllTeams(){
		Query q = em.createQuery("FROM Team");
		List<Team> teamList = q.getResultList();
		return teamList;
	}
	
	public List<TeamInstance> getAllTeamInstancesBySeason(Season season){
		TypedQuery<TeamInstance> q = em.createQuery("FROM TeamInstance WHERE season=:season",TeamInstance.class);
		q.setParameter("season", season);
		return q.getResultList();
	}
	
	public List<TeamInstance> getAllTeamInstances(){
		Query q = em.createQuery("FROM TeamInstance");
		List<TeamInstance> teamList = q.getResultList();
		return teamList;
	}
	
	public List<Team> getAllTeamsWithNoTeamInstanceForSeason(Season season){
		Query q = em.createQuery("FROM Team t WHERE t.id not in (FROM TeamInstance WHERE season=:season)");
		q.setParameter("season", season);
		List<Team> teamList = q.getResultList();
		return teamList;
	}
	
	public TeamInstance getTeamInstanceByTeamAndSeason(Team team, Season season){
		TypedQuery<TeamInstance> q = em.createQuery("FROM TeamInstance WHERE team=:team AND season=:season",TeamInstance.class);
		q.setParameter("season", season);
		q.setParameter("team", team);
		try{
			return q.getSingleResult();
		}
		catch (Exception e){
			System.out.println("Could not get single result team instance for team and season");
			e.printStackTrace();
		}
		return null;
	}
	
	public void removeTeamInstance(TeamInstance teamInstance){
		em.remove(teamInstance);
	}
	
}

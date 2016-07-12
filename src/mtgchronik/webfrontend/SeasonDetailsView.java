package mtgchronik.webfrontend;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import mtgchronik.entities.Season;
import mtgchronik.entities.Team;
import mtgchronik.entities.TeamInstance;
import mtgchronik.services.SeasonService;
import mtgchronik.services.TeamService;

@ManagedBean
@ViewScoped
public class SeasonDetailsView {
	
	@Inject
	private SeasonService seasonService;
	
	@Inject
	private TeamService teamService;
	
	private long id;
	private Season season;

	private Team team;
	private DualListModel<TeamInstance> teamInstances;
	private List<TeamInstance> teamInstancesForSeason;
	private List<TeamInstance> otherTeamInstances;
	
	public void loadSeason(){
		season = seasonService.getSeasonById(id);
		teamInstancesForSeason=teamService.getAllTeamInstancesBySeason(season);
		otherTeamInstances=new ArrayList<TeamInstance>();
		List<Team> tempList = teamService.getAllTeamsWithNoTeamInstanceForSeason(season);
		for (Team ti:tempList){
			otherTeamInstances.add(new TeamInstance(ti,season));
		}
		teamInstances=new DualListModel<TeamInstance>(teamInstancesForSeason,otherTeamInstances);
	}

	public long getSeasonId() {
		return id;
	}

	public void setSeasonId(long id) {
		this.id = id;
	}
	
	public List<TeamInstance> getTeamInstancesForSeason(){
		return teamService.getAllTeamInstancesBySeason(season);
	}
	
	
	public List<Team> getTeams(){
		return teamService.getAllTeams();
	}
	
	public void updateTeamInstances(){
		for (TeamInstance ti:teamInstances.getTarget())
			teamService.removeTeamInstance(ti);
		for (TeamInstance ti:teamInstances.getSource()){
			if (teamService.getTeamInstanceByTeamAndSeason(ti.getTeam(), season)==null){
				teamService.createTeamInstance(ti.getTeam(), season);
			}
		}
		//teamService.createTeamInstance(team, season);
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public DualListModel<TeamInstance> getTeamInstances() {
		return teamInstances;
	}

	public void setTeamInstances(DualListModel<TeamInstance> teamInstances) {
		this.teamInstances = teamInstances;
	}
	
	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}
}

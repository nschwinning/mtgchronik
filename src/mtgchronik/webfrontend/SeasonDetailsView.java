package mtgchronik.webfrontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
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
	private DualListModel<String> teamNames;
	private List<TeamInstance> teamInstancesForSeason;
	private HashMap<String,Team> teamMap;
	private boolean showTeamDetails=false;
	
	public void loadSeason(){
		season = seasonService.getSeasonById(id);
		teamInstancesForSeason=teamService.getAllTeamInstancesBySeason(season);
		teamMap = new HashMap<String,Team>();
		for (Team t:teamService.getAllTeams()){
			teamMap.put(t.getName(),t);
		}
		List<String> teamsForSeason = new ArrayList<String>();
		for (TeamInstance ti:teamInstancesForSeason){
			teamsForSeason.add(ti.getTeam().getName());
		}
		List<String> otherTeams = new ArrayList<String>();
		for (String name:teamMap.keySet()){
			if (!teamsForSeason.contains(name)){
				otherTeams.add(name);
			}
		}
		teamNames=new DualListModel<String>(otherTeams,teamsForSeason);
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
		for (TeamInstance ti:teamInstancesForSeason){
			System.out.println(ti.getTeam().getName() + ": " + ti.getLeague());
			teamService.updateTeamInstance(ti);
		}
	}
	
	public void handleTransfer(TransferEvent event) {
		for (String s:teamNames.getTarget()){
			if (teamService.getTeamInstanceByTeamNameAndSeason(s, season)==null){
				teamService.createTeamInstance(teamMap.get(s), season);
			}
		}
		for (String s:teamNames.getSource()){
			TeamInstance temp = teamService.getTeamInstanceByTeamNameAndSeason(s, season);
			if (temp!=null){
				teamService.removeTeamInstance(temp);
			}
		}
		teamInstancesForSeason=teamService.getAllTeamInstancesBySeason(season);
	}
	
	public void handleSelect(SelectEvent event){
		//TODO: needed?
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public DualListModel<String> getTeamNames() {
		return teamNames;
	}

	public void setTeamNames(DualListModel<String> teamNames) {
		this.teamNames = teamNames;
	}
	
	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public boolean isShowTeamDetails() {
		return showTeamDetails;
	}

	public void setShowTeamDetails(boolean showTeamDetails) {
		this.showTeamDetails = showTeamDetails;
	}
	
	public List<String> completeLeagueName(String name){
		List<String> leagues = teamService.getAllLeagueNames();
		List<String> leagueNames = new ArrayList<String>();
		for (String l:leagues){
			if (l!=null){
				if (l.toLowerCase().contains(name.toLowerCase())){
					leagueNames.add(l);
				}
			}
		}
		return leagueNames;
	}
}

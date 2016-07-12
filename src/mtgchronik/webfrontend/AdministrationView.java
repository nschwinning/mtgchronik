package mtgchronik.webfrontend;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mtgchronik.entities.Season;
import mtgchronik.entities.Team;
import mtgchronik.services.SeasonService;
import mtgchronik.services.TeamService;

@ManagedBean
@ViewScoped
public class AdministrationView {

	@Inject
	private SeasonService seasonService;
	
	@Inject
	private TeamService teamService;

	private int startYear;
	private int startYearSequence;
	private int endYearSequence;

	public void createSeason() {
		seasonService.createSeason(startYear);
	}

	public void createSeasonSequence() {
		if (startYearSequence<endYearSequence){
			for (int i=startYearSequence; i<endYearSequence; i++){
				seasonService.createSeason(i);
			}
		}
	}

	public List<Season> getAllSeasons() {
		return seasonService.getAllSeasons();
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	
	public int getStartYearSequence() {
		return startYearSequence;
	}

	public void setStartYearSequence(int startYearSequence) {
		this.startYearSequence = startYearSequence;
	}

	public int getEndYearSequence() {
		return endYearSequence;
	}

	public void setEndYearSequence(int endYear) {
		this.endYearSequence = endYear;
	}
	
	public String toSeasonDetails(Season season){
		return "/seasonDetails.xhtml";
	}
	
	public List<Team> getTeams(){
		return teamService.getAllTeams();
	}

	public void createTeams(){
		teamService.createTeam("1. Mannschaft");
		teamService.createTeam("2. Mannschaft");
		teamService.createTeam("3. Mannschaft");
		teamService.createTeam("4. Mannschaft");
		teamService.createTeam("5. Mannschaft");
		teamService.createTeam("6. Mannschaft");
		teamService.createTeam("7. Mannschaft");
	}
}

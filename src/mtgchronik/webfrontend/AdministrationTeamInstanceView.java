package mtgchronik.webfrontend;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mtgchronik.entities.TeamInstance;
import mtgchronik.services.TeamService;

@ManagedBean
@ViewScoped
public class AdministrationTeamInstanceView {

	@Inject
	private TeamService teamService;
	
	private long teamInstanceId;
	private TeamInstance teamInstance;
	
	public void loadTeamInstance(){
		this.teamInstance=teamService.getTeamInstanceByID(teamInstanceId);
	}

	public long getTeamInstanceId() {
		return teamInstanceId;
	}

	public void setTeamInstanceId(long teamInstanceId) {
		this.teamInstanceId = teamInstanceId;
		loadTeamInstance();
	}
	
	public String getWelcomeString(){
		return teamInstance.getTeam().getName() + ", Saison " + teamInstance.getSeason().getRepresentation();
	}
	
}

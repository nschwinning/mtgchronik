package mtgchronik.webfrontend;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mtgchronik.entities.LineUp;
import mtgchronik.entities.TeamInstance;
import mtgchronik.services.TeamService;

@ManagedBean
@ViewScoped
public class AdministrationTeamInstanceView {

	@Inject
	private TeamService teamService;
	
	private long teamInstanceId;
	private TeamInstance teamInstance;
	private LineUp lineUp;
	
	public void loadTeamInstance(){
		this.teamInstance=teamService.getTeamInstanceByID(teamInstanceId);
		this.setLineUp(teamService.getLineUpForTeamInstance(teamInstance));
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

	public LineUp getLineUp() {
		return lineUp;
	}

	public void setLineUp(LineUp lineUp) {
		this.lineUp = lineUp;
	}
	
	public void createLineUp(){
		lineUp=teamService.createLineUp(teamInstance);
	}
	
}

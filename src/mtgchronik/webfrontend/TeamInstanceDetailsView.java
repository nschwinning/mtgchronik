package mtgchronik.webfrontend;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mtgchronik.services.TeamService;

@ManagedBean
@ViewScoped
public class TeamInstanceDetailsView {
	
	@Inject
	private TeamService teamService;
	
	private long teamInstanceId;
	
	public void loadTeamInstance(){
		System.out.println("Hurra");
	}

	public long getTeamInstanceId() {
		return teamInstanceId;
	}

	public void setTeamInstanceId(long teamInstanceId) {
		this.teamInstanceId = teamInstanceId;
	}
	
}

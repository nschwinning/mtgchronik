package mtgchronik.webfrontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mtgchronik.entities.LineUp;
import mtgchronik.entities.Player;
import mtgchronik.entities.PlayerInstance;
import mtgchronik.entities.TeamInstance;
import mtgchronik.services.PlayerService;
import mtgchronik.services.TeamService;

@ManagedBean
@ViewScoped
public class AdministrationTeamInstanceView {

	@Inject
	private TeamService teamService;
	
	@Inject
	private PlayerService playerService;
	
	private long teamInstanceId;
	private TeamInstance teamInstance;
	private LineUp lineUp;
	private List<PlayerInstance> lineUpPlayers;
	private List<Player> players;
	
	public void loadTeamInstance(){
		this.teamInstance=teamService.getTeamInstanceByID(teamInstanceId);
		this.setLineUp(teamService.getLineUpForTeamInstance(teamInstance));
		if (lineUp!=null){
			lineUpPlayers = playerService.getPlayerInstancesForLineUp(lineUp);
		}
		else {
			lineUpPlayers = new ArrayList<PlayerInstance>();
		}
		players = playerService.getAllPlayers();
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
		for (int i=1;i<=6;i++){
			PlayerInstance playerInstance = playerService.createPlayerInstance(lineUp, i);
			lineUpPlayers.add(playerInstance);
		}
	}
	
	public List<PlayerInstance> getLineUpPlayers(){
		return lineUpPlayers;
	}
	
}

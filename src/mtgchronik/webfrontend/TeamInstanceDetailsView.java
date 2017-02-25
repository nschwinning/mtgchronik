package mtgchronik.webfrontend;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mtgchronik.comparators.PlayerInstanceComparator;
import mtgchronik.entities.LineUp;
import mtgchronik.entities.Player;
import mtgchronik.entities.PlayerInstance;
import mtgchronik.entities.Ranking;
import mtgchronik.entities.TableData;
import mtgchronik.entities.TeamInstance;
import mtgchronik.services.PlayerService;
import mtgchronik.services.TeamService;

@ManagedBean
@ViewScoped
public class TeamInstanceDetailsView {
	
	@Inject
	private TeamService teamService;
	
	@Inject
	private PlayerService playerService;
	
	private long teamInstanceId;
	private TeamInstance teamInstance;
	private LineUp lineUp;
	private List<PlayerInstance> lineUpPlayers;
	private List<Player> players;
	private Ranking ranking;
	private List<TableData> tableDataList;
	
	public void loadTeamInstance(){
		this.teamInstance=teamService.getTeamInstanceByID(teamInstanceId);
		this.lineUp = teamService.getLineUpForTeamInstance(teamInstance);
		this.ranking = teamService.getRankingForTeamInstance(teamInstance);
		if (lineUp!=null){
			lineUpPlayers = playerService.getPlayerInstancesForLineUp(lineUp);
			lineUpPlayers.sort(new PlayerInstanceComparator());
		}
		else {
			lineUpPlayers = new ArrayList<PlayerInstance>();
		}
		if (ranking!=null){
			tableDataList = teamService.getTableDataForRanking(ranking);
		}
		else{
			tableDataList = new ArrayList<TableData>();
		}
		setPlayers(playerService.getAllPlayers());
	}
	
	public TeamInstance getTeamInstance() {
		return teamInstance;
	}

	public void setTeamInstance(TeamInstance teamInstance) {
		this.teamInstance = teamInstance;
	}

	public long getTeamInstanceId() {
		return teamInstanceId;
	}

	public void setTeamInstanceId(long teamInstanceId) {
		this.teamInstanceId = teamInstanceId;
	}

	public LineUp getLineUp() {
		return lineUp;
	}

	public void setLineUp(LineUp lineUp) {
		this.lineUp = lineUp;
	}

	public List<PlayerInstance> getLineUpPlayers() {
		return lineUpPlayers;
	}

	public void setLineUpPlayers(List<PlayerInstance> lineUpPlayers) {
		this.lineUpPlayers = lineUpPlayers;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public List<TableData> getTableDataList() {
		return tableDataList;
	}

	public void setTableDataList(List<TableData> tableDataList) {
		this.tableDataList = tableDataList;
	}
	
}

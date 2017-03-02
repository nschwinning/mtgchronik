package mtgchronik.views;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mtgchronik.comparators.PlayerInstanceComparator;
import mtgchronik.comparators.TableDataComparator;
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
	private LineUp firstHalfLineUp;
	private LineUp secondHalfLineUp;
	private List<PlayerInstance> firstHalfLineUpPlayers;
	private List<PlayerInstance> secondHalfLineUpPlayers;
	private List<Player> players;
	private Ranking ranking;
	private List<TableData> tableDataList;
	
	public void loadTeamInstance(){
		this.teamInstance=teamService.getTeamInstanceByID(teamInstanceId);
		this.firstHalfLineUp = teamService.getLineUpForTeamInstance(teamInstance,1);
		this.secondHalfLineUp = teamService.getLineUpForTeamInstance(teamInstance,2);
		this.ranking = teamService.getRankingForTeamInstance(teamInstance);
		if (firstHalfLineUp!=null){
			firstHalfLineUpPlayers = playerService.getPlayerInstancesForLineUp(firstHalfLineUp);
			firstHalfLineUpPlayers.sort(new PlayerInstanceComparator());
		}
		else {
			firstHalfLineUpPlayers = new ArrayList<PlayerInstance>();
		}
		if (secondHalfLineUp!=null){
			secondHalfLineUpPlayers = playerService.getPlayerInstancesForLineUp(secondHalfLineUp);
			secondHalfLineUpPlayers.sort(new PlayerInstanceComparator());
		}
		else {
			secondHalfLineUpPlayers = new ArrayList<PlayerInstance>();
		}
		if (ranking!=null){
			tableDataList = teamService.getTableDataForRanking(ranking);
			tableDataList.sort(new TableDataComparator());
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

	public LineUp getFirstHalfLineUp() {
		return firstHalfLineUp;
	}

	public void setFirstHalfLineUp(LineUp firstHalfLineUp) {
		this.firstHalfLineUp = firstHalfLineUp;
	}

	public List<PlayerInstance> getFirstHalfLineUpPlayers() {
		return firstHalfLineUpPlayers;
	}

	public void setLineUpPlayers(List<PlayerInstance> firstHalfLineUpPlayers) {
		this.firstHalfLineUpPlayers = firstHalfLineUpPlayers;
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

	public LineUp getSecondHalfLineUp() {
		return secondHalfLineUp;
	}

	public void setSecondHalfLineUp(LineUp secondHalfLineUp) {
		this.secondHalfLineUp = secondHalfLineUp;
	}

	public List<PlayerInstance> getSecondHalfLineUpPlayers() {
		return secondHalfLineUpPlayers;
	}

	public void setSecondHalfLineUpPlayers(List<PlayerInstance> secondHalfLineUpPlayers) {
		this.secondHalfLineUpPlayers = secondHalfLineUpPlayers;
	}
	
}

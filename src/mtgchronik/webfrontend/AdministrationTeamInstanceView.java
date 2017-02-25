package mtgchronik.webfrontend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

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
public class AdministrationTeamInstanceView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8868226826302702982L;

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

	public long getTeamInstanceId() {
		return teamInstanceId;
	}

	public void setTeamInstanceId(long teamInstanceId) {
		this.teamInstanceId = teamInstanceId;
		loadTeamInstance();
	}
	
	public String getWelcomeString(){
		if (teamInstance==null)
			return "";
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
	
	public void onRowEdit(RowEditEvent event){
		for (PlayerInstance player:lineUpPlayers){
			playerService.updatePlayerInstance(player);
		}
	}
	
	public void onRowCancel(){}
	
	public void onTableRowEdit(RowEditEvent event){
		
	}
	
	public void onTableRowCancel(){}

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
	
	public void createTable(){
		ranking = teamService.createRanking(teamInstance);
		for (int i=1;i<=12;i++){
			TableData tableData = teamService.createTableData(ranking, i);
			tableDataList.add(tableData);
		}
	}
}

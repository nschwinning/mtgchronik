package mtgchronik.webfrontend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;

import mtgchronik.comparators.PlayerInstanceComparator;
import mtgchronik.comparators.TableDataComparator;
import mtgchronik.entities.LineUp;
import mtgchronik.entities.Player;
import mtgchronik.entities.PlayerInstance;
import mtgchronik.entities.Ranking;
import mtgchronik.entities.Resource;
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

	public LineUp getfirstHalfLineUp() {
		return firstHalfLineUp;
	}

	public void setfirstHalfLineUp(LineUp firstHalfLineUp) {
		this.firstHalfLineUp = firstHalfLineUp;
	}
	
	public void createFirstHalfLineUp(){
		firstHalfLineUp=teamService.createLineUp(teamInstance,1);
		for (int i=1;i<=6;i++){
			PlayerInstance playerInstance = playerService.createPlayerInstance(firstHalfLineUp, i);
			firstHalfLineUpPlayers.add(playerInstance);
		}
	}
	
	public void createSecondHalfLineUp(){
		secondHalfLineUp=teamService.createLineUp(teamInstance,2);
		for (int i=1;i<=6;i++){
			PlayerInstance playerInstance = playerService.createPlayerInstance(secondHalfLineUp, i);
			secondHalfLineUpPlayers.add(playerInstance);
		}
	}
	
	public List<PlayerInstance> getfirstHalfLineUpPlayers(){
		return firstHalfLineUpPlayers;
	}
	
	public void onRowEdit(RowEditEvent event){
		PlayerInstance player = (PlayerInstance) event.getObject();
		playerService.updatePlayerInstance(player);
	}
	
	public void onRowCancel(){}
	
	public void onTableRowEdit(RowEditEvent event){
		TableData data = (TableData)event.getObject();
		teamService.updateTableData(data);
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
	
	public TeamInstance getTeamInstance() {
		return teamInstance;
	}

	public void setTeamInstance(TeamInstance teamInstance) {
		this.teamInstance = teamInstance;
	}
	
	public List<String> completeLeague(String query){
		List<String> results = new ArrayList<String>();
		return results;
	}
	
	public void saveGeneralInformation(){
		teamService.updateTeamInstance(teamInstance);
	}
	
	 public void handleFileUpload(FileUploadEvent event) {
		 Resource resource = null;
		 if (teamInstance.getTeamPicture()==null){
			 resource = teamService.createResource(event.getFile().getFileName(), event.getFile().getContents());
		 }
		 else {
			 resource = teamInstance.getTeamPicture();
			 resource.setContent(event.getFile().getContents());
			 resource.setFileName(event.getFile().getFileName());
			 resource = teamService.updateResource(resource);
		 }
		 teamInstance.setTeamPicture(resource);
		 teamService.updateTeamInstance(teamInstance);
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

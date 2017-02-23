package mtgchronik.webfrontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import mtgchronik.entities.Season;
import mtgchronik.entities.Team;
import mtgchronik.entities.TeamInstance;
import mtgchronik.services.SeasonService;
import mtgchronik.services.TeamService;

@ManagedBean
@ViewScoped
public class IndexView {

	@Inject
	private SeasonService seasonService;
	
	@Inject
	private TeamService teamService;

	private int startYear;
	private String teamName;
	private TreeNode seasonRoot;
	private TreeNode selectedNode;
	private HashMap<Integer,TreeNode> seasonMap;
	private String newTeam;
	private List<String> availableTeams;
	private List<Team> allTeams;

	 @PostConstruct
	 public void init() {
		 loadSeasonTree();
		 allTeams=teamService.getAllTeams();
	 }
	 
	 public void loadSeasonTree(){
		 seasonRoot = new DefaultTreeNode("Root", null);
		 List<Season> seasonList = seasonService.getAllSeasons();
		 List<TeamInstance> teamInstanceList = teamService.getAllTeamInstances();
		 seasonMap = new HashMap<Integer,TreeNode>();
		 for (Season s:seasonList){
			 TreeNode tempNode = new DefaultTreeNode("season", s, seasonRoot);
			 seasonMap.put(s.getStartYear(), tempNode);
		 }
		 for (TeamInstance ti:teamInstanceList){
			 if (seasonMap.containsKey(ti.getSeason().getStartYear())){
				 TreeNode tempNode = new DefaultTreeNode("teamInstance",ti,seasonMap.get(ti.getSeason()));
				 seasonMap.get(ti.getSeason().getStartYear()).getChildren().add(tempNode);
			 }
			 else{
				 System.out.println("Season was not found for TeamInstance. Season is " + ti.getSeason().getRepresentation());
			 }
		 }
	 }
	 
	 public void onNodeSelect(NodeSelectEvent event){
		 if (selectedNode!=null){
			 selectedNode.setSelected(false);
		 }
		 selectedNode = event.getTreeNode();
		 selectedNode.setExpanded(!selectedNode.isExpanded());
	 }
	
	
	public void createSeason() {
		if (seasonService.getSeasonByStartYear(startYear)==null){
			seasonService.createSeason(startYear);
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", "Saison existiert bereits."));
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
	
	public String toSeasonDetails(Season season){
		return "/seasonDetails.xhtml";
	}
	
	public List<Team> getTeams(){
		return teamService.getAllTeams();
	}


	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public void createTeam(){
		if (teamName!=null&&!teamName.trim().isEmpty()&&teamService.getTeamByName(teamName)==null){
			teamService.createTeam(teamName.trim());
			teamName="";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", "Mannschaft existiert bereits."));
		}
	}


	public TreeNode getSeasonRoot() {
		return seasonRoot;
	}


	public void setSeasonRoot(TreeNode seasonRoot) {
		this.seasonRoot = seasonRoot;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public String getNewTeam() {
		return newTeam;
	}

	public void setNewTeam(String newTeam) {
		this.newTeam = newTeam;
	}

	public List<String> getAvailableTeams() {
		availableTeams=new ArrayList<String>();
		for (Team t:allTeams){
			availableTeams.add(t.getName());
		}
		return availableTeams;
	}

	public void setAvailableTeams(List<String> availableTeams) {
		this.availableTeams = availableTeams;
	}
	
	public void createTeamInstance(){
		if (selectedNode!=null){
			Season tempSeason = (Season)selectedNode.getData();
			if (teamService.getTeamInstanceByTeamNameAndSeason(newTeam, tempSeason)==null){
				teamService.createTeamInstance(teamService.getTeamByName(newTeam), tempSeason);
				loadSeasonTree();
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", "Team existiert bereits."));
			}
		}
	}

}

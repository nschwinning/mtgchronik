package mtgchronik.webfrontend;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import mtgchronik.entities.Player;
import mtgchronik.services.PlayerService;

@Dependent
public class AdministrationPlayersView {

	private List<Player> players;
	private Player selectedPlayer;
	private String newPlayerFirstName;
	private String newPlayerLastName;
	private boolean showDetails;
	
	@Inject
	private PlayerService playerService;
	
	@PostConstruct
	private void loadView(){
		refreshPlayers();
		showDetails=false;
	}
	
	private void refreshPlayers(){
		players=playerService.getAllPlayers();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(Player selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
		showDetails=true;
	}

	public String getNewPlayerFirstName() {
		return newPlayerFirstName;
	}

	public void setNewPlayerFirstName(String newPlayerFirstName) {
		this.newPlayerFirstName = newPlayerFirstName;
	}

	public String getNewPlayerLastName() {
		return newPlayerLastName;
	}

	public void setNewPlayerLastName(String newPlayerLastName) {
		this.newPlayerLastName = newPlayerLastName;
	}
	
	public void createPlayer(){
		if (playerService.getPlayerByName(newPlayerFirstName, newPlayerLastName)==null){
			if (!newPlayerFirstName.trim().isEmpty()&&!newPlayerLastName.trim().isEmpty()){
				playerService.createPlayer(newPlayerFirstName, newPlayerLastName);
				refreshPlayers();
				newPlayerFirstName="";
				newPlayerLastName="";
			}
		}
	}

	public boolean isShowDetails() {
		return showDetails;
	}

	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}
	
	public void closeSelectedPlayer(){
		showDetails=false;
	}
}

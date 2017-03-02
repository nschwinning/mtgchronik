package mtgchronik.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import mtgchronik.entities.Player;
import mtgchronik.services.PlayerService;

@Dependent
public class AdministrationPlayersView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3101512008168135725L;
	private List<Player> players;
	private Player selectedPlayer;
	private String newPlayerFirstName;
	private String newPlayerLastName;
	
	@Inject
	private PlayerService playerService;
	
	@PostConstruct
	private void loadView(){
		refreshPlayers();
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
		return selectedPlayer!=null;
	}
	
	public void closeSelectedPlayer(){
		selectedPlayer=null;
	}
}

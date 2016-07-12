package mtgchronik.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlayerInstance {

	@Id
    @GeneratedValue
    protected long id;
	
	@ManyToOne
	private Player player;
	
	@ManyToOne
	private Season season;
	
	@ManyToOne
	private TeamInstance teamInstance;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public TeamInstance getTeamInstance() {
		return teamInstance;
	}

	public void setTeamInstance(TeamInstance teamInstance) {
		this.teamInstance = teamInstance;
	}

	public long getId() {
		return id;
	}
	
	
	
}

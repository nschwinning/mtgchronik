package mtgchronik.entities;

import javax.persistence.Column;
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
	private LineUp lineUp;

	@Column
	private int position;
	
	@Column
	private int wins;

	@Column 
	private int losses;
	
	public PlayerInstance(){}
	
	public PlayerInstance(LineUp lineUp,int position){
		this.lineUp=lineUp;
		this.position=position;
		this.wins=0;
		this.losses=0;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public long getId() {
		return id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public LineUp getLineUp() {
		return lineUp;
	}

	public void setLineUp(LineUp lineUp) {
		this.lineUp = lineUp;
	}
	
	public String getPlayerName(){
		if (player==null)
			return "";
		return player.getFirstName() + " " + player.getLastName();
	}
	
	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}
	
}

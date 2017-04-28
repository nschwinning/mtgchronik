package mtgchronik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TableData extends AbstractEntity{
	
	@ManyToOne
	private Ranking table;
	
	@Column
	private int position;
	
	@Column
	private String teamName;
	
	@Column
	private int wins;
	
	@Column
	private int draws;
	
	@Column
	private int losses;
	
	@Column
	private int gameWins;
	
	@Column
	private int gameLosses;
	
	public TableData(){}
	
	public TableData(Ranking table, int position){
		this.table=table;
		this.position=position;
		this.wins=0;
		this.draws=0;
		this.losses=0;
	}

	public Ranking getTable() {
		return table;
	}

	public void setTable(Ranking table) {
		this.table = table;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public long getId() {
		return id;
	}
	
	public String getPoints(){
		int plusPoints = wins*2+draws;
		int minusPoints = draws + losses*2;
		return plusPoints + ":" + minusPoints;
	}

	public int getGameWins() {
		return gameWins;
	}

	public void setGameWins(int gameWins) {
		this.gameWins = gameWins;
	}

	public int getGameLosses() {
		return gameLosses;
	}

	public void setGameLosses(int gameLosses) {
		this.gameLosses = gameLosses;
	}
	
}

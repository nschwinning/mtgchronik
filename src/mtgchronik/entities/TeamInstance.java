package mtgchronik.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TeamInstance implements Serializable{

	@Id
    @GeneratedValue
    protected long id;
	
	@Column
	private Team team;
	
	@Column
	private Season season;
	
	public TeamInstance(){}
	
	public TeamInstance(Team team, Season season){
		this.team=team;
		this.season=season;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public long getId() {
		return id;
	}
	
	
}

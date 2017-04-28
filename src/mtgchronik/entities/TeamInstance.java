package mtgchronik.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TeamInstance extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7881208864490116584L;

	
	@ManyToOne
	private Team team;
	
	@ManyToOne
	private Season season;
	
	@Column
	private String league;
	
	@ManyToOne
	private Resource teamPicture;
	
	@Column(columnDefinition="boolean default false")
	private boolean visible;

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
	
	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public Resource getTeamPicture() {
		return teamPicture;
	}

	public void setTeamPicture(Resource teamPicture) {
		this.teamPicture = teamPicture;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}

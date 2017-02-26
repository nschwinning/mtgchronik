package mtgchronik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class LineUp extends AbstractEntity{
	
	@ManyToOne
	private TeamInstance teamInstance;
	
	@Column
	private int half;
	
	public LineUp(){}
	
	public LineUp(TeamInstance teamInstance, int half){
		this.teamInstance=teamInstance;
	}

}

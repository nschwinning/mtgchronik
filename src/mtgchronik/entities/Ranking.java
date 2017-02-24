package mtgchronik.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Ranking extends AbstractEntity{
	
	@OneToOne
	private TeamInstance teamInstance;
	
	public Ranking(){}
	
	public Ranking(TeamInstance teamInstance){
		this.teamInstance=teamInstance;
	}
	
}

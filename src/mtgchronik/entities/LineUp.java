package mtgchronik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LineUp {
	
	@Id
    @GeneratedValue
    protected long id;
	
	@OneToOne
	private TeamInstance teamInstance;
	
	public LineUp(){}
	
	public LineUp(TeamInstance teamInstance){
		this.teamInstance=teamInstance;
	}

}

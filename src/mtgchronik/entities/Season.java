package mtgchronik.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Season implements Serializable{

	@Id
    @GeneratedValue
    protected long id;
	
	@Column
	private int startYear;
	
	@Column
	private int endYear;
	
	public Season(){}
	
	public Season(int start){
		this.startYear=start;
		this.endYear=start+1;
	}
	
	public String getRepresentation(){
		return startYear + "/" + endYear;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public long getId() {
		return id;
	}
	
	
	
}

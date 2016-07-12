package mtgchronik.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Team implements Serializable{

	@Id
    @GeneratedValue
    protected long id;

	@Column(unique=true)
	private String name;
	
	public Team(){}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public Team(String name){
		this.name=name;
	}
	
}

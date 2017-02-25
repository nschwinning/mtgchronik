package mtgchronik.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Player extends AbstractEntity{

	@Column
	private String lastName;

	@Column
	private String firstName;

	public Player() {
	}

	public Player(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public long getId() {
		return id;
	}

	public String getFullName() {
		return lastName + ", " + firstName;
	}

	@Override
	public String toString() {
		return getFullName();
	}
}

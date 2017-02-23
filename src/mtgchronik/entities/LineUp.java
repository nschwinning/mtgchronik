package mtgchronik.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LineUp {
	
	@Id
    @GeneratedValue
    protected long id;

}

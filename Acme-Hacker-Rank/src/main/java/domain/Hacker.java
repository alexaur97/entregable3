package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Hacker extends DomainEntity {

	

	// Constructor .......................

	public Hacker() {
		super();
	}

	// Attributes ..................


}

package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Position extends DomainEntity {

	

	// Constructor .......................

	public Position() {
		super();
	}

	// Attributes ..................


}

package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Company extends DomainEntity {

	

	// Constructor .......................

	public Company() {
		super();
	}

	// Attributes ..................


}

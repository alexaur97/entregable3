package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	// Atributos Privados

	private String		name;
	private String		surnames;
	private Integer		VAT;
	private String		photo;
	private String		email;
	private String		phone;
	private String		address;
	private boolean 	spammer;
	private boolean		banned;


}

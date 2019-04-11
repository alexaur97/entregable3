package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	private Boolean copy;
	
	private PersonalData personalData;
	private Collection<EducationData> educationData;
	private Collection<MiscellaniusData> miscellaniusData;
	private Collection<PositionData> positionData;

	@NotNull
	public Boolean getCopy() {
		return copy;
	}

	public void setCopy(Boolean copy) {
		this.copy = copy;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	@OneToMany
	public Collection<EducationData> getEducationData() {
		return educationData;
	}

	public void setEducationData(Collection<EducationData> educationData) {
		this.educationData = educationData;
	}

	@OneToMany
	public Collection<MiscellaniusData> getMiscellaniusData() {
		return miscellaniusData;
	}

	public void setMiscellaniusData(Collection<MiscellaniusData> miscellaniusData) {
		this.miscellaniusData = miscellaniusData;
	}

	@OneToMany
	public Collection<PositionData> getPositionData() {
		return positionData;
	}

	public void setPositionData(Collection<PositionData> positionData) {
		this.positionData = positionData;
	}


}

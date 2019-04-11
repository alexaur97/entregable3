
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	private Boolean							copy;

	private PersonalData					personalData;
	private Collection<EducationData>		educationData;
	private Collection<MiscellaniusData>	miscellaniusData;
	private Collection<PositionData>		positionData;


	@NotNull
	public Boolean getCopy() {
		return this.copy;
	}

	public void setCopy(final Boolean copy) {
		this.copy = copy;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public PersonalData getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(final PersonalData personalData) {
		this.personalData = personalData;
	}

	@OneToMany
	public Collection<EducationData> getEducationData() {
		return this.educationData;
	}

	public void setEducationData(final Collection<EducationData> educationData) {
		this.educationData = educationData;
	}

	@OneToMany
	public Collection<MiscellaniusData> getMiscellaniusData() {
		return this.miscellaniusData;
	}

	public void setMiscellaniusData(final Collection<MiscellaniusData> miscellaniusData) {
		this.miscellaniusData = miscellaniusData;
	}

	@OneToMany
	public Collection<PositionData> getPositionData() {
		return this.positionData;
	}

	public void setPositionData(final Collection<PositionData> positionData) {
		this.positionData = positionData;
	}

}

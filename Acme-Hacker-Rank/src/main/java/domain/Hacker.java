package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Hacker extends Actor {
	
	private Collection<Curriculum> curriculums;

	@OneToMany
	public Collection<Curriculum> getCurriculums() {
		return curriculums;
	}

	public void setCurriculums(Collection<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}

}

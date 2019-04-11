
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Hacker extends Actor {

	private Collection<Curriculum>	curriculums;


	@OneToMany
	public Collection<Curriculum> getCurriculums() {
		return this.curriculums;
	}

	public void setCurriculums(final Collection<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}

}

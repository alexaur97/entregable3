
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;

@Service
@Transactional
public class CurriculumService {

	//Managed repository -------------------
	@Autowired
	private CurriculumRepository	curriculumRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public CurriculumService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Curriculum create() {
		Curriculum result;

		result = new Curriculum();

		return result;
	}

	public Collection<Curriculum> findAll() {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findAll();

		return result;
	}

	public Curriculum findOne(final int curriculumId) {
		Curriculum result;

		result = this.curriculumRepository.findOne(curriculumId);

		return result;
	}

	public void save(final Curriculum curriculum) {
		Assert.notNull(curriculum);

		this.curriculumRepository.save(curriculum);
	}

	public void delete(final Curriculum curriculum) {
		this.curriculumRepository.delete(curriculum);
	}

	//Other Methods--------------------
}

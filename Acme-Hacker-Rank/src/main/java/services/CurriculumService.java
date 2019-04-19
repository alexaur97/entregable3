
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.EducationData;
import domain.Hacker;
import domain.MiscellaniusData;
import domain.PersonalData;
import domain.PositionData;
import forms.CurriculumCreateForm;

@Service
@Transactional
public class CurriculumService {

	//Managed repository -------------------
	@Autowired
	private CurriculumRepository	curriculumRepository;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private PersonalDataService		personalDataService;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public CurriculumService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Curriculum create() {
		this.hackerService.findByPrincipal();
		Curriculum result;
		result = new Curriculum();
		result.setCopy(false);
		final Collection<EducationData> educationData = new ArrayList<>();
		final Collection<MiscellaniusData> miscellaniusData = new ArrayList<>();
		final Collection<PositionData> positionData = new ArrayList<>();
		result.setEducationData(educationData);
		result.setMiscellaniusData(miscellaniusData);
		result.setPositionData(positionData);
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

	public Curriculum save(final Curriculum curriculum) {
		final Hacker principal = this.hackerService.findByPrincipal();
		Assert.notNull(curriculum);
		final Curriculum result = this.curriculumRepository.save(curriculum);
		final Collection<Curriculum> curriculums = principal.getCurriculums();
		curriculums.add(result);
		principal.getCurriculums();
		this.hackerService.save(principal);
		return result;
	}

	public void delete(final Curriculum curriculum) {
		this.curriculumRepository.delete(curriculum);
	}

	public Collection<Curriculum> findAllByPrincipal() {
		final Hacker principal = this.hackerService.findByPrincipal();
		final Collection<Curriculum> result = this.curriculumRepository.findAllByPrincipal(principal.getId());
		return result;
	}

	public Curriculum constructByForm(final CurriculumCreateForm c) {
		final Curriculum result = this.create();
		final PersonalData a = this.personalDataService.create();
		a.setFullname(c.getFullname());
		a.setGithub(c.getGithub());
		a.setLinkedin(c.getLinkedin());
		a.setPhone(c.getPhone());
		a.setStatement(c.getStatement());
		result.setPersonalData(a);
		return result;
	}

	public Collection<Curriculum> findByHacker(final Integer idH) {
		final Collection<Curriculum> res = this.curriculumRepository.findAllByPrincipal(idH);
		return res;
	}

	public Curriculum findByPositionData(final int positionDataId) {
		final Curriculum res = this.curriculumRepository.findByPositonData(positionDataId);
		return null;
	}

	//Other Methods--------------------
}

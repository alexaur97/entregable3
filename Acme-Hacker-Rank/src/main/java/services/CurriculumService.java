
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

	//Other Methods--------------------

	public Collection<Curriculum> findByHacker(final Integer idH) {
		final Collection<Curriculum> res = this.curriculumRepository.findAllByPrincipal(idH);
		return res;
	}

	public Curriculum findByPositionData(final int positionDataId) {
		final Curriculum res = this.curriculumRepository.findByPositonData(positionDataId);
		return res;
	}

	public Curriculum savePositionData(final PositionData positionData) {
		final Curriculum res = this.findByPositionData(positionData.getId());
		res.getPositionData().add(positionData);
		return res;

	}

	public Curriculum deletePositionData(final PositionData pos) {
		final Curriculum res = this.findByPositionData(pos.getId());
		res.getPositionData().remove(pos);
		return res;
	}

	public Curriculum findByMiscellaneousData(final MiscellaniusData miscellaneousData) {
		final Curriculum res = this.curriculumRepository.findByMiscellaneousData(miscellaneousData.getId());
		return res;
	}

	public Curriculum deleteMiscellaneousData(final MiscellaniusData miscellaneousData) {
		final Curriculum res = this.findByPositionData(miscellaneousData.getId());
		res.getMiscellaniusData().remove(miscellaneousData);
		return res;
	}
	public Curriculum saveMiscellaneousData(final MiscellaniusData miscellaneousData) {
		final Curriculum res = this.findByPositionData(miscellaneousData.getId());
		res.getMiscellaniusData().add(miscellaneousData);
		return res;

	}

	public Curriculum findByEducationData(final EducationData educationData) {
		final Curriculum res = this.curriculumRepository.findByEducationData(educationData.getId());
		return res;
	}
	public Curriculum deleteEductationData(final EducationData educationData) {
		final Curriculum res = this.findByEducationData(educationData);
		res.getEducationData().remove(educationData);
		return res;
	}
	public Curriculum saveEducationData(final EducationData educationData) {
		final Curriculum res = this.findByPositionData(educationData.getId());
		res.getEducationData().add(educationData);
		return res;

	}
}

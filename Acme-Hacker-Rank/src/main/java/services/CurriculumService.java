
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

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

	@Autowired
	private EducationDataService	educationDataService;

	@Autowired
	private MiscellaniusDataService	miscellaniusDataService;

	@Autowired
	private PositionDataService		positionDataService;

	@Autowired
	private Validator				validator;


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
		final Hacker principal = this.hackerService.findByPrincipal();
		final Collection<Curriculum> curriculums = principal.getCurriculums();
		Assert.isTrue(curriculums.contains(curriculum));

		final Curriculum result = this.curriculumRepository.findOne(curriculum.getId());
		curriculums.remove(curriculum);
		principal.setCurriculums(curriculums);
		this.hackerService.save(principal);
		this.curriculumRepository.delete(result);
	}
	public Collection<Curriculum> findAllByPrincipal() {
		final Hacker principal = this.hackerService.findByPrincipal();
		final Collection<Curriculum> result = this.curriculumRepository.findAllByPrincipal(principal.getId());
		return result;
	}

	public Curriculum constructByForm(final CurriculumCreateForm c) {
		final Curriculum result = this.create();
		result.setIdName(c.getIdName());
		final PersonalData a = this.personalDataService.create();
		a.setFullname(c.getFullname());
		a.setGithub(c.getGithub());
		a.setLinkedin(c.getLinkedin());
		a.setPhone(c.getPhone());
		a.setStatement(c.getStatement());
		result.setPersonalData(a);
		return result;
	}

	public Curriculum reconstruct(final Curriculum curriculum, final BindingResult binding) {
		final Curriculum result = this.findOne(curriculum.getId());
		result.setIdName(curriculum.getIdName());
		this.validator.validate(result, binding);
		return result;
	}

	public Curriculum findByPersonalData(final int id) {
		this.hackerService.findByPrincipal();
		final Curriculum result = this.curriculumRepository.findByPersonalData(id);
		return result;
	}

	public Curriculum copyCurriculum(final Curriculum curriculum) {
		final Hacker principal = this.hackerService.findByPrincipal();
		Assert.isTrue(principal.getCurriculums().contains(curriculum));

		final Curriculum copy = this.create();
		copy.setIdName(curriculum.getIdName());
		copy.setCopy(true);

		final PersonalData personalData = this.copyPersonalData(curriculum.getPersonalData());

		final Collection<EducationData> educationDatas = this.copyEducationData(curriculum.getEducationData());
		final Collection<MiscellaniusData> miscellaniusDatas = this.copyMiscellaniusData(curriculum.getMiscellaniusData());
		final Collection<PositionData> positionDatas = this.copyPositionData(curriculum.getPositionData());

		copy.setPersonalData(personalData);
		copy.setEducationData(educationDatas);
		copy.setMiscellaniusData(miscellaniusDatas);
		copy.setPositionData(positionDatas);

		final Curriculum result = this.save(copy);
		return result;
	}

	private Collection<PositionData> copyPositionData(final Collection<PositionData> positionDatas) {
		final Collection<PositionData> result = new ArrayList<>();
		for (final PositionData p : positionDatas) {
			final PositionData positionData = this.positionDataService.create();
			positionData.setDescription(p.getDescription());
			positionData.setEndDate(p.getEndDate());
			positionData.setStartDate(p.getStartDate());
			positionData.setTitle(p.getTitle());
			result.add(positionData);
		}
		return result;
	}

	private Collection<MiscellaniusData> copyMiscellaniusData(final Collection<MiscellaniusData> miscellaniusDatas) {
		final Collection<MiscellaniusData> result = new ArrayList<>();
		for (final MiscellaniusData m : miscellaniusDatas) {
			final MiscellaniusData miscellaniusData = this.miscellaniusDataService.create();
			miscellaniusData.setAttachments(m.getAttachments());
			miscellaniusData.setText(m.getText());
			result.add(miscellaniusData);
		}
		return result;
	}

	private Collection<EducationData> copyEducationData(final Collection<EducationData> educationDatas) {
		final Collection<EducationData> result = new ArrayList<>();
		for (final EducationData e : educationDatas) {
			final EducationData educationData = this.educationDataService.create();
			educationData.setDegree(e.getDegree());
			educationData.setEndDate(e.getEndDate());
			educationData.setInstitution(e.getInstitution());
			educationData.setMark(e.getMark());
			educationData.setStartDate(e.getStartDate());
			result.add(educationData);
		}
		return result;
	}

	private PersonalData copyPersonalData(final PersonalData personalData) {
		final PersonalData result = this.personalDataService.create();
		result.setFullname(personalData.getFullname());
		result.setGithub(personalData.getGithub());
		result.setLinkedin(personalData.getLinkedin());
		result.setPhone(personalData.getPhone());
		result.setStatement(personalData.getStatement());
		return result;
	}

	//Other Methods--------------------
}

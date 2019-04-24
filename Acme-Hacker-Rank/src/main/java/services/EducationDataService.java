
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationDataRepository;
import domain.EducationData;

@Service
@Transactional
public class EducationDataService {

	//Managed repository -------------------
	@Autowired
	private EducationDataRepository	educationDataRepository;

	@Autowired
	private HackerService			hackerService;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public EducationDataService() {
		super();
	}

	//Simple CRUD methods--------------------

	public EducationData create() {
		EducationData result;

		result = new EducationData();

		return result;
	}

	public Collection<EducationData> findAll() {
		Collection<EducationData> result;

		result = this.educationDataRepository.findAll();

		return result;
	}

	public EducationData findOne(final int educationDataId) {
		EducationData result;

		result = this.educationDataRepository.findOne(educationDataId);

		return result;
	}

	public void save(final EducationData educationData) {
		Assert.notNull(educationData);
		if (educationData.getEndDate() != null)
			Assert.isTrue(educationData.getStartDate().before(educationData.getEndDate()));

		this.educationDataRepository.save(educationData);
	}

	public void delete(final EducationData educationData) {
		this.hackerService.findByPrincipal();
		this.educationDataRepository.delete(educationData);
	}

	public void delete(final Collection<EducationData> educationDatas) {
		this.educationDataRepository.delete(educationDatas);

	}

	//Other Methods--------------------
}

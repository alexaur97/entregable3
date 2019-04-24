
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MiscellaniusDataRepository;
import domain.Curriculum;
import domain.MiscellaniusData;

@Service
@Transactional
public class MiscellaniusDataService {

	//Managed repository -------------------
	@Autowired
	private MiscellaniusDataRepository	miscellaniusDataRepository;

	@Autowired
	private CurriculumService			curriculumService;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public MiscellaniusDataService() {
		super();
	}

	//Simple CRUD methods--------------------

	public MiscellaniusData create() {
		MiscellaniusData result;

		result = new MiscellaniusData();

		return result;
	}

	public Collection<MiscellaniusData> findAll() {
		Collection<MiscellaniusData> result;

		result = this.miscellaniusDataRepository.findAll();

		return result;
	}

	public MiscellaniusData findOne(final int miscellaniusDataId) {
		MiscellaniusData result;

		result = this.miscellaniusDataRepository.findOne(miscellaniusDataId);

		return result;
	}

	public void save(final MiscellaniusData miscellaniusData) {
		Assert.notNull(miscellaniusData);
		if (miscellaniusData.getId() != 0) {
			final Curriculum c = this.curriculumService.findByMiscellaneousData(miscellaniusData);
			Assert.isTrue(c.getCopy() == false);
		}
		this.miscellaniusDataRepository.save(miscellaniusData);
	}

	public void delete(final MiscellaniusData miscellaniusData) {
		final Curriculum c = this.curriculumService.findByMiscellaneousData(miscellaniusData);
		Assert.isTrue(c.getCopy() == false);
		this.miscellaniusDataRepository.delete(miscellaniusData);
	}

	//Other Methods--------------------
}

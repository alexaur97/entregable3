
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionDataRepository;
import domain.PositionData;

@Service
@Transactional
public class PositionDataService {

	//Managed repository -------------------
	@Autowired
	private PositionDataRepository	positionDataRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public PositionDataService() {
		super();
	}

	//Simple CRUD methods--------------------

	public PositionData create() {
		PositionData result;

		result = new PositionData();

		return result;
	}

	public Collection<PositionData> findAll() {
		Collection<PositionData> result;

		result = this.positionDataRepository.findAll();

		return result;
	}

	public PositionData findOne(final int positionDataId) {
		PositionData result;

		result = this.positionDataRepository.findOne(positionDataId);

		return result;
	}

	public void save(final PositionData positionData) {
		Assert.notNull(positionData);

		this.positionDataRepository.save(positionData);
	}

	public void delete(final PositionData positionData) {
		this.positionDataRepository.delete(positionData);
	}

	//Other Methods--------------------
}

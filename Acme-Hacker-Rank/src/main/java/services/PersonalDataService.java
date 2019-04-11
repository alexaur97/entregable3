
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalDataRepository;
import domain.PersonalData;

@Service
@Transactional
public class PersonalDataService {

	//Managed repository -------------------
	@Autowired
	private PersonalDataRepository	personalDataRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public PersonalDataService() {
		super();
	}

	//Simple CRUD methods--------------------

	public PersonalData create() {
		PersonalData result;

		result = new PersonalData();

		return result;
	}

	public Collection<PersonalData> findAll() {
		Collection<PersonalData> result;

		result = this.personalDataRepository.findAll();

		return result;
	}

	public PersonalData findOne(final int personalDataId) {
		PersonalData result;

		result = this.personalDataRepository.findOne(personalDataId);

		return result;
	}

	public void save(final PersonalData personalData) {
		Assert.notNull(personalData);

		this.personalDataRepository.save(personalData);
	}

	public void delete(final PersonalData personalData) {
		this.personalDataRepository.delete(personalData);
	}

	//Other Methods--------------------
}

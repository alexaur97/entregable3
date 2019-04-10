
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;

@Service
@Transactional
public class ApplicationService {

	//Managed repository -------------------
	@Autowired
	private ApplicationRepository	applicationRepository;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public ApplicationService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Application create() {
		Application result;

		result = new Application();

		return result;
	}

	public Collection<Application> findAll() {
		Collection<Application> result;

		result = this.applicationRepository.findAll();

		return result;
	}

	public Application findOne(final int applicationId) {
		Application result;

		result = this.applicationRepository.findOne(applicationId);

		return result;
	}

	public void save(final Application application) {
		Assert.notNull(application);

		this.applicationRepository.save(application);
	}

	public void delete(final Application application) {
		this.applicationRepository.delete(application);
	}

	//Other Methods--------------------

	public Collection<Double> statsApplicationsPerHacker() {
		final Collection<Double> result = this.applicationRepository.statsApplicationsPerHacker();
		Assert.notNull(result);
		return result;
	}
}

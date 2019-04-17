
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ApplicationRepository;
import domain.Application;
import domain.Company;
import domain.Problem;

@Service
@Transactional
public class ApplicationService {

	//Managed repository -------------------
	@Autowired
	private ApplicationRepository	applicationRepository;
	@Autowired
	private CompanyService			companyService;
	@Autowired
	private HackerService			hackerService;
	@Autowired
	private ProblemService			problemService;
	@Autowired
	private Validator				validator;


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
	public Application saveCompany(final Application application) {
		final Application result;
		Assert.notNull(application);
		final Company company = this.companyService.findByPrincipal();
		Assert.isTrue(company.equals(application.getPosition().getCompany()));
		result = this.applicationRepository.save(application);
		return result;

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

	public void delete(final Collection<Application> applications) {
		this.applicationRepository.delete(applications);
	}

	public Collection<Application> findAllByProblem(final int id) {
		final Collection<Application> result = this.applicationRepository.findAllByProblem(id);
		return result;
	}

	//Other Methods--------------------
	public Collection<Application> findApplicationsByCompany(final int id) {
		final Collection<Application> result = this.applicationRepository.findApplicationsByCompany(id);
		return result;
	}
	public Application acceptApplicationChanges(final int applicationId) {
		Assert.notNull(applicationId);
		final Company company = this.companyService.findByPrincipal();
		final Application application = this.applicationRepository.findOne(applicationId);
		Assert.isTrue(company.equals(application.getPosition().getCompany()));
		Assert.isTrue(application.getStatus().equals("SUBMITTED"));
		application.setStatus("ACCEPTED");
		return application;

	}
	public Application rejectApplicationChanges(final int applicationId) {
		Assert.notNull(applicationId);
		final Company company = this.companyService.findByPrincipal();
		final Application application = this.applicationRepository.findOne(applicationId);
		Assert.isTrue(company.equals(application.getPosition().getCompany()));
		Assert.isTrue(application.getStatus().equals("SUBMITTED"));
		application.setStatus("REJECTED");
		return application;

	}
	public Collection<Application> findApplicationsByHacker(final int id) {
		final Collection<Application> result = this.applicationRepository.findApplicationsByHacker(id);
		return result;
	}
	public Application recostructionCreate(final Application application, final BindingResult binding) {
		application.setHacker(this.hackerService.findByPrincipal());
		final Date moment = new Date();
		application.setMoment(moment);
		application.setStatus("PENDING");
		if (!(application.getPosition() == null)) {
			final Problem problem = this.problemService.findProblemByPosition(application.getPosition().getId());
			application.setProblem(problem);
		}
		this.validator.validate(application, binding);
		return application;

	}
}

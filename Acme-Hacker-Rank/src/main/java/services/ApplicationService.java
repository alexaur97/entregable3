
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.ApplicationRepository;
import domain.Application;
import domain.Company;

@Service
@Transactional
public class ApplicationService {

	//Managed repository -------------------
	@Autowired
	private ApplicationRepository	applicationRepository;
	@Autowired
	private CompanyService			companyService;


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
		if (application.getStatus() == "REJECTED") {

			Assert.isTrue(!(application.getExplanation() == null));
			if (!(application.getExplanation() == null))
				Assert.isTrue(!(application.getExplanation().isEmpty()));
		}
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
	public void restriccionesRejectGet(final Integer applicationId) {
		Assert.notNull(applicationId);
		final Company company = this.companyService.findByPrincipal();
		final Application application = this.applicationRepository.findOne(applicationId);
		Assert.isTrue(company.equals(application.getPosition().getCompany()));
		Assert.isTrue(application.getStatus().equals("SUBMITTED"));

	}
	public Application rejectRecostruction(final Application application, final BindingResult binding) {
		final Application res = application;
		final Application a = this.findOne(application.getId());
		res.setStatus("REJECTED");
		res.setCodeLink(a.getCodeLink());
		res.setCurriculum(a.getCurriculum());
		res.setHacker(a.getHacker());
		res.setMoment(a.getMoment());
		res.setPosition(a.getPosition());
		res.setProblem(a.getProblem());
		res.setSubmitMoment(a.getSubmitMoment());
		return res;
	}
}

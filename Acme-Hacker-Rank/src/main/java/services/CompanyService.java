
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CompanyRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Company;

@Service
@Transactional
public class CompanyService {

	//Managed repository -------------------
	@Autowired
	private CompanyRepository	companyRepository;

	//Servicios
	private ActorService		actorService;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public CompanyService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Company create() {
		Company result;

		result = new Company();

		return result;
	}

	public Collection<Company> findAll() {
		Collection<Company> result;

		result = this.companyRepository.findAll();

		return result;
	}

	public Company findOne(final int companyId) {
		Company result;

		result = this.companyRepository.findOne(companyId);

		return result;
	}

	public void save(final Company company) {
		Assert.notNull(company);

		this.companyRepository.save(company);
	}

	public void delete(final Company company) {
		this.companyRepository.delete(company);
	}

	//Other Methods--------------------

	public Company findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Company c = this.findByUserId(user.getId());
		Assert.notNull(c);
		this.actorService.auth(c, Authority.COMPANY);
		return c;
	}

	private Company findByUserId(final int id) {
		final Company c = this.companyRepository.findByUserId(id);
		return c;
	}
}

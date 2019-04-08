
package services;

import java.util.ArrayList;
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
import domain.CreditCard;
import forms.CompanyRegisterForm;

@Service
@Transactional
public class CompanyService {

	//Managed repository -------------------
	@Autowired
	private CompanyRepository	companyRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private CreditCardService	creditCardService;


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
	public Company constructByForm(final CompanyRegisterForm companyRegisterForm) {
		final Company result = this.create();
		result.setAddress(companyRegisterForm.getAddress());
		result.setBanned(false);
		result.setCommercialName(companyRegisterForm.getCommercialName());
		final CreditCard creditCard = new CreditCard();
		creditCard.setBrandName(companyRegisterForm.getBrandName());
		creditCard.setCvv(companyRegisterForm.getCvv());
		creditCard.setExpirationMonth(companyRegisterForm.getExpirationMonth());
		creditCard.setExpirationYear(companyRegisterForm.getExpirationYear());
		creditCard.setHolderName(companyRegisterForm.getHolderName());
		creditCard.setNumber(companyRegisterForm.getNumber());
		result.setCreditCard(creditCard);
		result.setEmail(companyRegisterForm.getEmail());
		result.setName(companyRegisterForm.getName());
		result.setPhone(companyRegisterForm.getPhone());
		result.setPhoto(companyRegisterForm.getPhoto());
		result.setSpammer(false);
		result.setSurnames(companyRegisterForm.getSurnames());
		final UserAccount userAccount = new UserAccount();
		userAccount.setPassword(companyRegisterForm.getPassword());
		userAccount.setUsername(companyRegisterForm.getUsername());
		final Collection<Authority> authorities = new ArrayList<>();
		//		final Authority auth = Authority.COMPANY;
		//		authorities.add(auth);
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		result.setVAT(companyRegisterForm.getVAT());
		return result;
	}
}


package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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

<<<<<<< HEAD
	@Autowired
	private CreditCardService	creditCardService;


=======
>>>>>>> master
	//Supporting Services ------------------

	//COnstructors -------------------------
	public CompanyService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Company create() {
		final Company result = new Company();
		final UserAccount ua = new UserAccount();

		result.setUserAccount(ua);

		final Authority a = new Authority();
		a.setAuthority(Authority.COMPANY);
		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(a);
		result.getUserAccount().setAuthorities(authorities);

		final CreditCard creditCard = new CreditCard();
		result.setCreditCard(creditCard);
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

	public Company save(final Company company) {
		Assert.notNull(company);

		final Company result = this.companyRepository.save(company);
		System.out.println(result);
		return result;
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
		Assert.isTrue(companyRegisterForm.getPassword().equals(companyRegisterForm.getConfirmPassword()));
		final Company result = this.create();
		final Collection<String> emails = this.actorService.findAllEmails();
		final String email = companyRegisterForm.getEmail();
		final Boolean bEmail = !emails.contains(email);
		Assert.isTrue(bEmail);

		final Collection<String> accounts = this.actorService.findAllAccounts();
		final UserAccount userAccount = result.getUserAccount();
		final Boolean bAccount = !accounts.contains(companyRegisterForm.getUsername());
		Assert.isTrue(bAccount);

		final Md5PasswordEncoder pe = new Md5PasswordEncoder();
		final String password = pe.encodePassword(companyRegisterForm.getPassword(), null);
		userAccount.setPassword(password);
		userAccount.setUsername(companyRegisterForm.getUsername());
		result.setUserAccount(userAccount);

		result.setAddress(companyRegisterForm.getAddress());
		result.setBanned(false);
		result.setCommercialName(companyRegisterForm.getCommercialName());

		final CreditCard creditCard = result.getCreditCard();
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
		final Authority auth = new Authority();
		auth.setAuthority(Authority.COMPANY);
		authorities.add(auth);
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		result.setVAT(companyRegisterForm.getVAT());
		return result;
}
}

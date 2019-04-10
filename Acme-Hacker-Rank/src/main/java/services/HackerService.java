
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HackerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.CreditCard;
import domain.Hacker;
import forms.HackerRegisterForm;

@Service
@Transactional
public class HackerService {

	//Managed repository -------------------
	@Autowired
	private HackerRepository	hackerRepository;

	//Supporting Services ------------------
	@Autowired
	private ActorService		actorService;


	//COnstructors -------------------------
	public HackerService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Hacker create() {
		final Hacker result = new Hacker();
		final UserAccount ua = new UserAccount();

		result.setUserAccount(ua);

		final Authority a = new Authority();
		a.setAuthority(Authority.HACKER);
		final Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(a);
		result.getUserAccount().setAuthorities(authorities);

		final CreditCard creditCard = new CreditCard();
		result.setCreditCard(creditCard);
		return result;
	}

	public Collection<Hacker> findAll() {
		Collection<Hacker> result;

		result = this.hackerRepository.findAll();

		return result;
	}

	public Hacker findOne(final int hackerId) {
		Hacker result;

		result = this.hackerRepository.findOne(hackerId);

		return result;
	}

	public Hacker save(final Hacker hacker) {
		Assert.notNull(hacker);

		final Hacker result = this.hackerRepository.save(hacker);
		System.out.println(result);
		return result;
	}

	public void delete(final Hacker hacker) {
		this.hackerRepository.delete(hacker);
	}

	//Other Methods--------------------

	private Hacker findByUserId(final int id) {
		final Hacker h = this.hackerRepository.findByUserId(id);
		return h;
	}

	public Hacker findByPrincipal() {
		final UserAccount user = LoginService.getPrincipal();
		Assert.notNull(user);

		final Hacker h = this.findByUserId(user.getId());
		Assert.notNull(h);
		this.actorService.auth(h, Authority.HACKER);
		return h;
	}

	public Hacker constructByForm(final HackerRegisterForm hackerRegisterForm) {
		Assert.isTrue(hackerRegisterForm.getPassword().equals(hackerRegisterForm.getConfirmPassword()));
		final Hacker result = this.create();
		final Collection<String> emails = this.actorService.findAllEmails();
		final String email = hackerRegisterForm.getEmail();
		final Boolean bEmail = !emails.contains(email);
		Assert.isTrue(bEmail);

		final Collection<String> accounts = this.actorService.findAllAccounts();
		final UserAccount userAccount = result.getUserAccount();
		final Boolean bAccount = !accounts.contains(hackerRegisterForm.getUsername());
		Assert.isTrue(bAccount);

		final Md5PasswordEncoder pe = new Md5PasswordEncoder();
		final String password = pe.encodePassword(hackerRegisterForm.getPassword(), null);
		userAccount.setPassword(password);
		userAccount.setUsername(hackerRegisterForm.getUsername());
		result.setUserAccount(userAccount);

		result.setAddress(hackerRegisterForm.getAddress());
		result.setBanned(false);

		final CreditCard creditCard = result.getCreditCard();
		creditCard.setBrandName(hackerRegisterForm.getBrandName());
		creditCard.setCvv(hackerRegisterForm.getCvv());
		creditCard.setExpirationMonth(hackerRegisterForm.getExpirationMonth());
		creditCard.setExpirationYear(hackerRegisterForm.getExpirationYear());
		creditCard.setHolderName(hackerRegisterForm.getHolderName());
		creditCard.setNumber(hackerRegisterForm.getNumber());
		result.setCreditCard(creditCard);
		result.setEmail(hackerRegisterForm.getEmail());
		result.setName(hackerRegisterForm.getName());
		result.setPhone(hackerRegisterForm.getPhone());
		result.setPhoto(hackerRegisterForm.getPhoto());
		result.setSpammer(false);
		result.setSurnames(hackerRegisterForm.getSurnames());
		result.setVAT(hackerRegisterForm.getVAT());
		return result;
	}

	public Collection<Hacker> hackersHaveMadeMoreApplications() {
		final Collection<Hacker> result = this.hackerRepository.hackersHaveMadeMoreApplications();
		Assert.notNull(result);
		return result;
	}

}

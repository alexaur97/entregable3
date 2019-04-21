
package services;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Message;
import forms.ActorEditForm;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository	actorRepository;

	@Autowired
	private MessageService	messageService;


	public Actor save(final Actor a) {
		Assert.notNull(a);
		return this.actorRepository.save(a);

	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Actor result;

		result = this.actorRepository.findByUserAccount(userAccount.getId());
		return result;
	}

	public Actor findByPrincipal() {
		UserAccount userAccount;
		Actor result;

		userAccount = LoginService.getPrincipal();
		result = this.findByUserAccount(userAccount);
		return result;
	}

	public Actor findOne(final int id) {

		Assert.isTrue(id != 0);

		Actor result;

		result = this.actorRepository.findOne(id);
		Assert.notNull(result);
		return null;
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;
		result = this.actorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Boolean auth(final Actor a, final String auth) {
		final UserAccount userAccount = a.getUserAccount();
		final Collection<Authority> allAuths = userAccount.getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(auth);
		final Boolean res = allAuths.contains(au);
		Assert.isTrue(res);
		return res;
	}

	public Collection<String> findAllAccounts() {
		final Collection<String> result = this.actorRepository.findAllAccounts();
		return result;
	}

	public boolean validateCountryCode(final String phoneNumber) {
		final String regex = "[0-9]+";
		final Pattern patt = Pattern.compile(regex);
		Boolean b = true;
		final Matcher matcher = patt.matcher(phoneNumber);
		if (!matcher.matches())
			b = false;
		return b;
	}

	public Collection<String> findAllEmails() {
		final Collection<String> result = this.actorRepository.findAllEmails();
		return result;
	}

	public Boolean authEdit(final Actor a, final String auth) {
		final UserAccount userAccount = a.getUserAccount();
		final Collection<Authority> allAuths = userAccount.getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(auth);
		final Boolean res = allAuths.contains(au);
		return res;
	}

	public ActorEditForm toForm(final Actor actor) {
		final ActorEditForm res = new ActorEditForm();
		res.setName(actor.getName());
		res.setSurnames(actor.getSurnames());
		res.setVAT(actor.getVAT());
		res.setPhoto(actor.getPhoto());
		res.setEmail(actor.getEmail());
		res.setPhone(actor.getPhone());
		res.setAddress(actor.getAddress());
		return res;
	}

	public void isSpammer() {

		final List<Actor> actores = this.actorRepository.findAll();
		for (int i = 0; i < actores.size(); i++) {
			final int actorId = actores.get(i).getId();

			final Collection<Message> messages = this.messageService.findSender(actorId);
			final Collection<Message> messagesSpam = this.messageService.findSenderSpam(actorId);
			if (messagesSpam.size() / messages.size() > 10)
				actores.get(i).setSpammer(true);
			else
				actores.get(i).setSpammer(false);
		}
	}
}

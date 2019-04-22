
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import domain.Actor;
import domain.Administrator;
import domain.Message;
import domain.SpamWord;

@Service
@Transactional
public class MessageService {

	//Managed repository -------------------
	@Autowired
	private MessageRepository		messageRepository;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private SpamWordService			spamWordService;

	@Autowired
	private Validator				validator;


	//Supporting Services ------------------

	//COnstructors -------------------------
	public MessageService() {
		super();
	}

	//Simple CRUD methods--------------------

	public Message create() {
		Message result;

		result = new Message();

		return result;
	}

	public Collection<Message> findAll() {
		Collection<Message> result;

		result = this.messageRepository.findAll();

		return result;
	}

	public Message findOne(final int messageId) {
		Message result;

		result = this.messageRepository.findOne(messageId);

		return result;
	}

	public void save(final Message message) {
		Assert.notNull(message);

		this.messageRepository.save(message);
	}

	public void delete(final Message message) {
		this.messageRepository.delete(message);
	}

	//Other Methods--------------------

	public Collection<Message> findRecives(final int id) {
		final Collection<Message> result = this.messageRepository.findRecives(id);
		Assert.notNull(result);
		return result;
	}

	public Collection<Message> findSend(final int id) {
		Collection<Message> result;
		Assert.notNull(id);
		result = this.messageRepository.findSend(id);
		Assert.notNull(result);
		return result;
	}

	public Collection<Message> findSpam(final int id) {
		Collection<Message> result;
		Assert.notNull(id);
		result = this.messageRepository.findSpam(id);
		Assert.notNull(result);
		return result;

	}

	public Collection<Message> findSender(final int id) {
		Collection<Message> result;
		Assert.notNull(id);
		result = this.messageRepository.findSender(id);
		Assert.notNull(result);
		return result;

	}

	public Collection<Message> findSenderSpam(final int id) {
		Collection<Message> result;
		Assert.notNull(id);
		result = this.messageRepository.findSenderSpam(id);
		Assert.notNull(result);
		return result;

	}

	public Collection<Message> findDeleted(final int id) {
		Collection<Message> result;
		Assert.notNull(id);
		result = this.messageRepository.findDeleted(id);
		Assert.notNull(result);
		return result;
	}

	public Message reconstruct(final Message msg, final BindingResult binding) {
		final Message res = msg;
		final Actor a = this.actorService.findByPrincipal();
		msg.setSender(a);
		msg.setOwner(a);
		final Date moment = new Date();
		msg.setMoment(moment);
		msg.setSpam(false);
		msg.setDeleted(false);
		msg.setCopy(false);
		this.validator.validate(res, binding);
		return res;
	}
	//CUANDO EXISTA LAS SPAM WORDS
	public Boolean spam(final Message msg) {

		Boolean res = false;
		final Collection<String> sw = new ArrayList<>();
		for (final String s : sw) {
			if (msg.getBody().contains(s))
				res = true;
			if (msg.getSubject().contains(s))
				res = true;
		}
		return res;
	}
	public Message reconstructAdmnistrator(final Message msg, final BindingResult binding) {
		final Administrator admin = this.administratorService.findByPrincipal();
		msg.setDeleted(false);
		final Date moment = new Date();
		msg.setMoment(moment);
		msg.setRecipient(admin);
		msg.setSender(admin);
		final Collection<String> tags = new ArrayList<>();
		tags.add("SYSTEM");
		msg.setTags(tags);
		msg.setSpam(this.spam(msg));
		msg.setOwner(admin);
		this.validator.validate(msg, binding);
		return msg;
	}
	public Message reconstructAdmnistrator2(final Message msg, final Actor actor, final BindingResult binding) {
		msg.setRecipient(actor);
		this.validator.validate(msg, binding);
		return msg;
	}

	public List<String> spamwords(final Collection<SpamWord> sw) {

		Collection<SpamWord> spamwords = new ArrayList<>();
		spamwords = this.spamWordService.findAll();
		final List<SpamWord> list = new ArrayList<>(spamwords);
		final List<String> res = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			final String palabra = list.get(i).getWord();
			res.add(palabra);
		}

		return res;

	}

	public void isSpam(final Message message) {
		Collection<SpamWord> spamwords = new ArrayList<>();
		spamwords = this.spamWordService.findAll();
		final String[] mensaje = message.getBody().trim().split(" ");
		final List<String> lista = Arrays.asList(mensaje);

		final List<String> sw = this.spamwords(spamwords);

		for (int j = 0; j < lista.size(); j++)
			if (sw.contains(lista.get(j))) {
				message.setSpam(true);
				final Collection<String> tags = new ArrayList<>();
				tags.add("SPAM");
				message.setTags(tags);
				break;
			} else
				message.setSpam(false);
	}

	public void changedStatus(final Actor actor) {
		final Message message = this.create();
		message.setRecipient(actor);
		message.setOwner(actor);
		final Date moment = new Date();
		message.setMoment(moment);
		message.setCopy(true);
		message.setDeleted(false);
		message.setSender(null);
		message.setSpam(false);
		message.setSubject("System message");
		message.setBody("One of your applications has changed its status / Una de tus aplicaciones ha cambiado su estado.");
		final Collection<String> tags = new ArrayList<>();
		tags.add("SYSTEM");
		message.setTags(tags);
		this.save(message);

	}

}

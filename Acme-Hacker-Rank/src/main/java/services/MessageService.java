
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import domain.Message;

@Service
@Transactional
public class MessageService {

	//Managed repository -------------------
	@Autowired
	private MessageRepository	messageRepository;

	@Autowired
	private Validator			validator;


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

	public Collection<Message> findDeleted(final int id) {
		Collection<Message> result;
		Assert.notNull(id);
		result = this.messageRepository.findDeleted(id);
		Assert.notNull(result);
		return result;
	}

	public Message reconstruct(final Message msg, final BindingResult binding) {
		final Message res = msg;
		this.validator.validate(res, binding);
		return res;
	}
}

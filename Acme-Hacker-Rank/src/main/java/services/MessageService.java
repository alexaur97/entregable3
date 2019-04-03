package services; 

import java.util.Collection; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.util.Assert; 

import repositories.MessageRepository;

import domain.Message; 

@Service 
@Transactional 
public class MessageService { 

	//Managed repository -------------------
	@Autowired
	private MessageRepository messageRepository;


	//Supporting Services ------------------


	//COnstructors -------------------------
	public MessageService(){
		super();
	}


	//Simple CRUD methods--------------------

	public Message create(){
		Message result;

		result = new Message();

		return result;
	}

	public Collection<Message> findAll(){
		Collection<Message> result;

		result = messageRepository.findAll();

		return result;
	}

	public Message findOne(int messageId){
		Message result;

		result = messageRepository.findOne(messageId);

		return result;
	}

	public void save(Message message){
		Assert.notNull(message);

		messageRepository.save(message);
	}

	public void delete(Message message){
		messageRepository.delete(message);
	}


	//Other Methods--------------------
} 

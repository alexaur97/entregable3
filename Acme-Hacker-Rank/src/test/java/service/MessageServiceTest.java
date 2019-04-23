
package service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ActorService;
import services.MessageService;
import utilities.AbstractTest;
import domain.Actor;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	@Autowired
	private MessageService	messageService;

	@Autowired
	private ActorService	actorService;


	//Requisito 23.2 Un Actor autenticado puede enviar un mensaje a otro
	@Test
	public void testCreateMessageGood() throws ParseException {
		super.authenticate("company1");
		final Actor recipient = this.actorService.findByPrincipal();
		final Message msg = this.messageService.create();
		msg.setSubject("Test subject");
		msg.setBody("Test body");
		final ArrayList<String> tags = new ArrayList<>();
		tags.add("tag1");
		tags.add("tag2");
		final ArrayList<String> attachments = new ArrayList<>();
		attachments.add("tag1");
		attachments.add("tag2");
		msg.setAttachments(attachments);
		msg.setTags(tags);
		msg.setRecipient(recipient);

		final Message message = this.messageService.reconstruct(msg, null);
		this.messageService.save(message);
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateMessageError() throws ParseException {
		super.authenticate(null);
		final Actor recipient = this.actorService.findByPrincipal();
		final Message msg = this.messageService.create();
		msg.setSubject("Test subject");
		msg.setBody("Test body");
		final ArrayList<String> tags = new ArrayList<>();
		tags.add("tag1");
		tags.add("tag2");
		msg.setTags(tags);
		msg.setRecipient(recipient);
		final ArrayList<String> attachments = new ArrayList<>();
		attachments.add("tag1");
		attachments.add("tag2");
		msg.setAttachments(attachments);
		final Message message = this.messageService.reconstruct(msg, null);
		this.messageService.save(message);
		super.unauthenticate();
	}

	//Requisito 23.2 Un Actor autenticado puede eliminar definitivamente uno de sus mensajes
	@Test
	public void testDeleteMessageGood() throws ParseException {
		super.authenticate("company1");
		final Actor a = this.actorService.findByPrincipal();
		final Collection<Message> msgs = this.messageService.findSend(a.getId());
		final String tag = "DELETED";
		for (final Message m : msgs) {
			m.getTags().add(tag);
			m.setDeleted(true);
			this.messageService.save(m);
		}
		final Collection<Message> msgd = this.messageService.findDeleted(a.getId());
		for (final Message me : msgd)
			this.messageService.delete(me);

		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteMessageError() throws ParseException {
		super.authenticate(null);
		final Actor a = this.actorService.findByPrincipal();
		final Collection<Message> msgs = this.messageService.findSend(a.getId());
		final String tag = "DELETED";
		for (final Message m : msgs) {
			m.getTags().add(tag);
			m.setDeleted(true);
			this.messageService.save(m);
		}
		final Collection<Message> msgd = this.messageService.findDeleted(a.getId());
		for (final Message me : msgd)
			this.messageService.delete(me);

		super.unauthenticate();
	}
}

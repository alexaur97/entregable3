package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	private String subject;
	private String body;
	public Date moment;
	private Collection<String> attachments;
	public Boolean spam;

	public Actor recipient;
	public Actor sender;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@ElementCollection
	@NotEmpty
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public Collection<String> getAttachments() {
		return attachments;
	}
	public void setAttachments(Collection<String> attachments) {
		this.attachments = attachments;
	}
	
	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)	
	public Boolean getSpam() {
		return spam;
	}
	public void setSpam(Boolean spam) {
		this.spam = spam;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Actor getRecipient() {
		return recipient;
	}
	public void setRecipient(Actor recipient) {
		this.recipient = recipient;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Actor getSender() {
		return sender;
	}
	public void setSender(Actor sender) {
		this.sender = sender;
	}
}

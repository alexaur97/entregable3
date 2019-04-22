
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	private String				subject;
	private String				body;
	public Date					moment;
	private Collection<String>	attachments;
	private Collection<String>	tags;
	public Boolean				spam;
	public Boolean				deleted;
	public Actor				recipient;
	public Actor				sender;
	public Integer				owner;
	public Boolean				copy;


	@NotNull
	public Integer getOwner() {
		return this.owner;
	}

	public void setOwner(final Integer owner) {
		this.owner = owner;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSubject() {
		return this.subject;
	}
	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBody() {
		return this.body;
	}
	public void setBody(final String body) {
		this.body = body;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Past
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@ElementCollection
	@NotEmpty
	public Collection<String> getAttachments() {
		return this.attachments;
	}
	public void setAttachments(final Collection<String> attachments) {
		this.attachments = attachments;
	}

	@ElementCollection
	public Collection<String> getTags() {
		return this.tags;
	}
	public void setTags(final Collection<String> tags) {
		this.tags = tags;
	}

	@NotNull
	public Boolean getSpam() {
		return this.spam;
	}
	public void setSpam(final Boolean spam) {
		this.spam = spam;
	}

	@NotNull
	public Boolean getCopy() {
		return this.copy;
	}
	public void setCopy(final Boolean copy) {
		this.copy = copy;
	}

	@NotNull
	public Boolean getDeleted() {
		return this.deleted;
	}
	public void setDeleted(final Boolean deleted) {
		this.deleted = deleted;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Actor getRecipient() {
		return this.recipient;
	}
	public void setRecipient(final Actor recipient) {
		this.recipient = recipient;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}
	public void setSender(final Actor sender) {
		this.sender = sender;
	}
}

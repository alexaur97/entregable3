package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {
	
	private Date moment;
	private String explanation;
	private String codeLink;
	private Date submitMoment;
	private String status;
	
	private Hacker hacker;
	private Problem problem;
	private Position position;	
	private Curriculum curriculum;	
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getCodeLink() {
		return codeLink;
	}
	public void setCodeLink(String codeLink) {
		this.codeLink = codeLink;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:ss HH:mm")
	public Date getSubmitMoment() {
		return submitMoment;
	}
	public void setSubmitMoment(Date submitMoment) {
		this.submitMoment = submitMoment;
	}
	
	@Pattern(regexp = "^ACCEPTED|REJECTED|SUBMITTED$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Hacker getHacker() {
		return hacker;
	}
	public void setHacker(Hacker hacker) {
		this.hacker = hacker;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Problem getProblem() {
		return problem;
	}
	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}

package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Position extends DomainEntity {

	private String title;
	private String description;
	private Date deadline;
	private String profileRequired;
	private String skillRequired;
	private String techRequired;
	private Integer salaryOffered;
	private String ticker;
	private String mode;
	private Boolean cancelled;
	
	private Company company;
	private Collection<Problem> problems;
	
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:ss")
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getProfileRequired() {
		return profileRequired;
	}
	public void setProfileRequired(String profileRequired) {
		this.profileRequired = profileRequired;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSkillRequired() {
		return skillRequired;
	}
	public void setSkillRequired(String skillRequired) {
		this.skillRequired = skillRequired;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTechRequired() {
		return techRequired;
	}
	public void setTechRequired(String techRequired) {
		this.techRequired = techRequired;
	}
	
	@Min(0)
	@NotNull
	public Integer getSalaryOffered() {
		return salaryOffered;
	}
	public void setSalaryOffered(Integer salaryOffered) {
		this.salaryOffered = salaryOffered;
	}
	
	//formatodelticker
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	@NotBlank
	@Pattern(regexp = "^DRAFT|FINAL$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	@NotNull
	public Boolean getCancelled() {
		return cancelled;
	}
	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@NotNull
	@ManyToMany
	public Collection<Problem> getProblems() {
		return problems;
	}
	public void setProblems(Collection<Problem> problems) {
		this.problems = problems;
	}

}

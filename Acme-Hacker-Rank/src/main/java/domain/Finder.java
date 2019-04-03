package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	private String keyword;
	private Date deadline;
	private Integer minSalary;
	private Integer maxSalary;
	
	private Hacker hacker;
	
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@SafeHtml(whitelistType = WhiteListType.NONE)	
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	@Min(0)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public Integer getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}
	
	@Min(0)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public Integer getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Hacker getHacker() {
		return hacker;
	}
	public void setHacker(Hacker hacker) {
		this.hacker = hacker;
	}
	


}

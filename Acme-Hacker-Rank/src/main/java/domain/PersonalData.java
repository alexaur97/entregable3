package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class PersonalData extends DomainEntity {

	private String fullname;
	private String statement;
	private String phone;
	private String github;
	private String linkedin;
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getStatement() {
		return statement;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getPhone() {
		return phone;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGithub() {
		return github;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public void setGithub(String github) {
		this.github = github;
	}
	
	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

}


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

	private String	fullname;
	private String	statement;
	private String	phone;
	private String	github;
	private String	linkedin;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getFullname() {
		return this.fullname;
	}
	public void setFullname(final String fullname) {
		this.fullname = fullname;
	}
	public String getStatement() {
		return this.statement;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public void setStatement(final String statement) {
		this.statement = statement;
	}
	public String getPhone() {
		return this.phone;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public void setPhone(final String phone) {
		this.phone = phone;
	}
	public String getGithub() {
		return this.github;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public void setGithub(final String github) {
		this.github = github;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getLinkedin() {
		return this.linkedin;
	}
	public void setLinkedin(final String linkedin) {
		this.linkedin = linkedin;
	}

}

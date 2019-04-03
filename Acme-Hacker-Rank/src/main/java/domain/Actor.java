package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	// Atributos Privados

	private String		name;
	private String		surnames;
	private Integer		VAT;
	private CreditCard creditCard;
	private String		photo;
	private String		email;
	private String		phone;
	private String		address;
	private Boolean 	spammer;
	private Boolean		banned;

	public UserAccount	userAccount;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public Integer getVAT() {
		return VAT;
	}

	public void setVAT(Integer vAT) {
		VAT = vAT;
	}

	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Column(unique = true)
	@Pattern(regexp = "([a-zA-Z0-9])+@([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)*|[a-zA-Z0-9]+[ a-zA-Z0-9]*\\<([a-zA-Z0-9])+@([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)*\\>")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public Boolean isSpammer() {
		return spammer;
	}

	public void setSpammer(Boolean spammer) {
		this.spammer = spammer;
	}

	@NotNull
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public Boolean isBanned() {
		return banned;
	}

	public void setBanned(Boolean banned) {
		this.banned = banned;
	}

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}

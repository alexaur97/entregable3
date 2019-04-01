package domain;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
public class CreditCard {
	
	//Attributes
	private String holderName;
	private String brandName;
	private String number;
	private Integer expirationMonth;
	private Integer expirationYear;
	private Integer cvv;
	

	
	//Constructor
	public CreditCard(){
		this.brandName  		="";
		this.holderName 		="";
		this.number    			="";
		this.expirationMonth 	=12;
		this.expirationYear  	=2016;
		this.cvv				=100;
	}
	public CreditCard(String holderName,String brandName,String number, Integer expirationMonth, Integer expirationYear,Integer ccv){
		this.holderName  		=holderName;
		this.brandName 			=brandName;
		this.number    			=number;
		this.expirationMonth 	=expirationMonth;
		this.expirationYear  	=expirationYear;
		this.cvv				=ccv;
	}
	
	public CreditCard(String creditCard){
		String[] array = creditCard.split(",");
		
		this.holderName=array[0];
		this.brandName=array[1];
		this.number=array[2];
		this.expirationMonth=Integer.parseInt(array[3]);
		this.expirationYear=Integer.parseInt(array[4]);
		this.cvv=Integer.parseInt(array[5]);
		
	}
	
	
	
	@NotBlank
	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	@NotBlank
	@CreditCardNumber
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Range(min=1,max=12)
	public Integer getExpirationMonth() {

		return expirationMonth;
	}

	public void setExpirationMonth(Integer expirationMonth) {


		this.expirationMonth = expirationMonth;
	}
	
	@Range(min=2016,max=9999)
	public Integer getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(Integer expirationYear) {
		
		this.expirationYear = expirationYear;
	}

	@Range(min=100,max=999)
	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	
	
}

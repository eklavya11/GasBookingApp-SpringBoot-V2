package com.egasbooking.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//CustomerBank
@Entity
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	
	@NotNull(message = "accountNo is required")
	@Column(unique = true)
	private String accountNo;
	
	
	private String ifscNo;
	
	@NotEmpty(message = "bankName is required")
	private String bankName;
	
	@NotEmpty(message = "addessLine1 is required")
	private String addessLine1;
	
	@NotEmpty(message = "addessLine2 is required")
	private String addessLine2;
	
	@NotEmpty(message = "city is required")
	private String city;
	
	@NotEmpty(message = "state is required")
	private String state;
	
	@NotEmpty(message = "zipCode is required")
	
	private String zipCode;
	
	@NotEmpty(message = "country is required")
	private String country;

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscNo() {
		return ifscNo;
	}

	public void setIfscNo(String ifscNo) {
		this.ifscNo = ifscNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAddessLine1() {
		return addessLine1;
	}

	public void setAddessLine1(String addessLine1) {
		this.addessLine1 = addessLine1;
	}

	public String getAddessLine2() {
		return addessLine2;
	}

	public void setAddessLine2(String addessLine2) {
		this.addessLine2 = addessLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Bank(int bankId, @NotNull(message = "accountNo is required") String accountNo,
			@Size(min = 11, max = 11) String ifscNo, @NotEmpty(message = "bankName is required") String bankName,
			@NotEmpty(message = "addessLine1 is required") String addessLine1,
			@NotEmpty(message = "addessLine2 is required") String addessLine2,
			@NotEmpty(message = "city is required") String city, @NotEmpty(message = "state is required") String state,
			@NotEmpty(message = "zipCode is required") @Size(min = 6, max = 6) String zipCode,
			@NotEmpty(message = "country is required") String country) {
		super();
		this.bankId = bankId;
		this.accountNo = accountNo;
		this.ifscNo = ifscNo;
		this.bankName = bankName;
		this.addessLine1 = addessLine1;
		this.addessLine2 = addessLine2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	public Bank() {
		super();
		
	}

	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", accountNo=" + accountNo + ", ifscNo=" + ifscNo + ", bankName=" + bankName
				+ ", addessLine1=" + addessLine1 + ", addessLine2=" + addessLine2 + ", city=" + city + ", state="
				+ state + ", zipCode=" + zipCode + ", country=" + country + "]";
	}
	

}

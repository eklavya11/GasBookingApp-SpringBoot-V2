package com.egasbooking.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "user_details")
public abstract class AbstractUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotEmpty(message = "username is required")
	private String username;
	//@JsonIgnore
	@Size(min = 8)
	private String password;
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Mobile number is invalid")
	private String mobileNumber;
	@NotEmpty(message = "addessLine1 is required")
	private String addessLine1;
	@NotEmpty(message = "addessLine2 is required")
	private String addessLine2;
	@NotEmpty(message = "city is required")
	private String city;
	@NotEmpty(message = "state is required")
	private String state;
	@NotEmpty(message = "zipCode is required")
	@Size(min = 6, max = 6)
	private String zipCode;
	@NotEmpty(message = "country is required")
	private String country;

	@NotEmpty(message = "Email is required")
	@Column(unique = true)
	private String email;

	protected AbstractUser() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	protected AbstractUser(int userId, @NotEmpty(message = "username is required") String username,
			@Size(min = 8, max = 10) String password,
			@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Mobile number is invalid") String mobileNumber,
			@NotEmpty(message = "addessLine1 is required") String addessLine1,
			@NotEmpty(message = "addessLine2 is required") String addessLine2,
			@NotEmpty(message = "city is required") String city, @NotEmpty(message = "state is required") String state,
			@NotEmpty(message = "zipCode is required") @Size(min = 6, max = 6) String zipCode,
			@NotEmpty(message = "country is required") String country,
			@NotEmpty(message = "Email is required") String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.addessLine1 = addessLine1;
		this.addessLine2 = addessLine2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
		this.email = email;
	}

}

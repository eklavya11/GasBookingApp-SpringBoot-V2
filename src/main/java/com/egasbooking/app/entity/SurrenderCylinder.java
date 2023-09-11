package com.egasbooking.app.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SurrenderCylinder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int surrenderId;
   @JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate surrenderDate;

	@OneToOne(cascade = CascadeType.MERGE)
	private Customer customer;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cylinder_fk", referencedColumnName = "cylinderId")
	private Cylinder cylinder;

	public SurrenderCylinder() {

	}

	public SurrenderCylinder(int surrenderId, LocalDate surrendarDate, Customer customer, Cylinder cylinder) {
		super();
		this.surrenderId = surrenderId;
		this.surrenderDate = surrendarDate;
		this.customer = customer;
		this.cylinder = cylinder;
	}

	public SurrenderCylinder(String string, int i) {
		
	}

	public int getSurrenderId() {
		return surrenderId;
	}

	public void setSurrenderId(int surrenderId) {
		this.surrenderId = surrenderId;
	}

	public LocalDate getSurrendarDate() {
		return surrenderDate;
	}

	public void setSurrendarDate(LocalDate surrendarDate) {
		this.surrenderDate = surrendarDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Cylinder getCylinder() {
		return cylinder;
	}

	public void setCylinder(Cylinder cylinder) {
		this.cylinder = cylinder;
	}

	@Override
	public String toString() {
		return "SurrenderCylinder [surrenderId=" + surrenderId + ", surrenderDate=" + surrenderDate + ", customer="
				+ customer + ", cylinder=" + cylinder + "]";
	}

}

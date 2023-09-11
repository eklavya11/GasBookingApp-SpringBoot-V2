package com.egasbooking.app.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GasBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gas_booking_id")
	private int gasBookingId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate deliveryDate;
	private boolean status;
	private float bill;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_fk")
	private Customer customer;

	public GasBooking() {

	}

	public int getGasBookingId() {
		return gasBookingId;
	}

	public void setGasBookingId(int gasBookingId) {
		this.gasBookingId = gasBookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "GasBooking [gasBookingId=" + gasBookingId + ", bookingDate=" + bookingDate + ", deliveryDate="
				+ deliveryDate + ", status=" + status + ", bill=" + bill + ", customer=" + customer + "]";
	}

}

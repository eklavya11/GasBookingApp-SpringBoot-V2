package com.egasbooking.app.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GasBookingUpdateDto {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate deliveryDate;
	private boolean status;
	private float bill;
	private int customerId;

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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public GasBookingUpdateDto() {
		super();
	
	}

	public GasBookingUpdateDto(LocalDate bookingDate, LocalDate deliveryDate, boolean status, float bill,
			int customerId) {
		super();
		this.bookingDate = bookingDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.bill = bill;
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "GasBookingUpdateDto [bookingDate=" + bookingDate + ", deliveryDate=" + deliveryDate + ", status="
				+ status + ", bill=" + bill + ", customerId=" + customerId + "]";
	}

}

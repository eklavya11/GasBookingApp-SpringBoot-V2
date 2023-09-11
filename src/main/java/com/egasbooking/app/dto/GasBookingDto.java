package com.egasbooking.app.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GasBookingDto {

	private int gasBookingId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;
	private boolean status;
	private float bill;

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
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
	

	public int getGasBookingId() {
		return gasBookingId;
	}

	public void setGasBookingId(int gasBookingId) {
		this.gasBookingId = gasBookingId;
	}

	public GasBookingDto(LocalDate bookingDate, boolean status, float bill) {
		super();
		this.bookingDate = bookingDate;
		this.status = status;
		this.bill = bill;
	}

	public GasBookingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GasBookingDto [gasBookingId=" + gasBookingId + ", bookingDate=" + bookingDate + ", status=" + status
				+ ", bill=" + bill + "]";
	}

}

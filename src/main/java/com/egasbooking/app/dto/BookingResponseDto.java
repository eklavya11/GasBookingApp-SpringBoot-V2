package com.egasbooking.app.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingResponseDto {

	private int gasBookingId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate deliveryDate;
	private boolean status;
	private float bill;
	private String bookingMessage;

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

	public String getBookingMessage() {
		return bookingMessage;
	}

	public void setBookingMessage(String bookingMessage) {
		this.bookingMessage = bookingMessage;
	}

	public BookingResponseDto() {
		super();
		
	}

	public BookingResponseDto(int gasBookingId, LocalDate bookingDate, LocalDate deliveryDate, boolean status,
			float bill, String bookingMessage) {
		super();
		this.gasBookingId = gasBookingId;
		this.bookingDate = bookingDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.bill = bill;
		this.bookingMessage = bookingMessage;
	}

	@Override
	public String toString() {
		return "BookingResponseDto [gasBookingId=" + gasBookingId + ", bookingDate=" + bookingDate + ", deliveryDate="
				+ deliveryDate + ", status=" + status + ", bill=" + bill + ", bookingMessage=" + bookingMessage + "]";
	}

}

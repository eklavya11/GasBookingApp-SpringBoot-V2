package com.egasbooking.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egasbooking.app.dto.BookingResponseDto;
import com.egasbooking.app.dto.GasBookingDto;
import com.egasbooking.app.dto.GasBookingUpdateDto;
import com.egasbooking.app.entity.Customer;
import com.egasbooking.app.entity.GasBooking;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.repository.CustomerRepository;
import com.egasbooking.app.repository.GasBookingRepository;

@Service
public class GasBookingServiceImpl implements GasBookingService {

	@Autowired
	private GasBookingRepository gasBookingRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public BookingResponseDto insertGasBooking(GasBookingDto gasBookingDto, int customerId)
			throws CustomerException, ParseException {
		GasBooking gasBooking = new GasBooking();
		BookingResponseDto bookingResponseDto = new BookingResponseDto();
		try {
			Customer customer = customerRepository.findById(customerId).get();

			List<GasBooking> allBookingByCustomerId = gasBookingRepository.getAllBookingByCustomerId(customerId);
			for (GasBooking gasBooking2 : allBookingByCustomerId) {
				LocalDate bookingDateOld = gasBooking2.getBookingDate();

				Date toDate = new Date();
				LocalDateTime to = LocalDateTime.ofInstant(toDate.toInstant(), ZoneId.systemDefault());
					System.out.println("to "+to);
					LocalDateTime bookingDateOldconvt = bookingDateOld.atStartOfDay();
					System.out.println("bookingDateOldconvt "+bookingDateOldconvt);
					long days = Duration.between(bookingDateOldconvt, to).toDays();
					System.out.println("days diff "+days);
				if (Duration.between(bookingDateOldconvt, to).toDays() <= 30) {
					bookingResponseDto.setBookingMessage("can't be booked");
					bookingResponseDto.setStatus(false);
					return bookingResponseDto;
				}

			}

			if (customer != null) {
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(gasBookingDto.getBookingDate().toString());
				LocalDate bookingDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				gasBooking.setBookingDate(bookingDate);
				gasBooking.setDeliveryDate(bookingDate.plusDays(7));
				gasBooking.setCustomer(customer);
				gasBooking.setStatus(true);
				gasBooking.setBill(customer.getCylinder().getPrice());
				GasBooking savebooking = gasBookingRepository.save(gasBooking);
				bookingResponseDto.setBill(savebooking.getBill());
				bookingResponseDto.setBookingDate(savebooking.getBookingDate());
				bookingResponseDto.setStatus(savebooking.isStatus());
				bookingResponseDto.setDeliveryDate(savebooking.getDeliveryDate());
				bookingResponseDto.setGasBookingId(savebooking.getGasBookingId());
				bookingResponseDto.setBookingMessage("Gas is booked and delivery date is one week from booking date "
						+ savebooking.getDeliveryDate());

				return bookingResponseDto;
			}
		} catch (NoSuchElementException e) {
			throw new CustomerException("No Such Customer found !!!");
		}
		return null;
	}

	@Override
	public BookingResponseDto updateGasBooking(GasBookingUpdateDto gasBookingUpdateDto, int gasBookingId)
			throws ResourceNotFoundException, CustomerException, ParseException {
		BookingResponseDto bookingResponseDto = new BookingResponseDto();
		try {
			GasBooking gasBooking = gasBookingRepository.findById(gasBookingId).get();
			if (gasBooking != null) {
				Customer customer = customerRepository.findById(gasBookingUpdateDto.getCustomerId()).get();
				if (customer != null) {
					Date date1 = new SimpleDateFormat("yyyy-MM-dd")
							.parse(gasBookingUpdateDto.getBookingDate().toString());
					LocalDate bookingDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					gasBooking.setBookingDate(bookingDate);
					gasBooking.setDeliveryDate(bookingDate.plusDays(7));
					gasBooking.setCustomer(customer);
					gasBooking.setStatus(true);
					gasBooking.setBill(customer.getCylinder().getPrice());
					GasBooking savebooking = gasBookingRepository.save(gasBooking);
					bookingResponseDto.setBill(savebooking.getBill());
					bookingResponseDto.setBookingDate(savebooking.getBookingDate());
					bookingResponseDto.setStatus(savebooking.isStatus());
					bookingResponseDto.setDeliveryDate(savebooking.getDeliveryDate());
					bookingResponseDto.setGasBookingId(savebooking.getGasBookingId());
					bookingResponseDto.setBookingMessage("Gas Booking is updated now");

					return bookingResponseDto;
				} else {
					throw new CustomerException("No Such Customer found !!!");
				}
			}
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Gas Booking With this id: " + gasBookingId + " not found!!!");
		}
		return null;
	}

	@Override
	public GasBooking deleteGasBooking(int gasBookingId) throws ResourceNotFoundException {
		try {
			GasBooking result = gasBookingRepository.findById(gasBookingId).get();
			if (result != null) {
				gasBookingRepository.delete(result);
				return result;
			}
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Gas booking with this id:" + gasBookingId + " not found");
		}

		return null;
	}

	@Override
	public List<GasBooking> getBill(int customerId) throws CustomerException {
		List<GasBooking> gasBooking = gasBookingRepository.findByCustomerId(customerId);
		if (gasBooking != null) {
			return gasBooking;
		} else {
			throw new CustomerException("Customer with customer id:" + customerId + " not found!!!");
		}
	}

	@Override
	public List<GasBooking> getBookingDate(LocalDate bookingDate) throws ResourceNotFoundException {
		List<GasBooking> gasBooking = gasBookingRepository.findByBookingDate(bookingDate);
		if (gasBooking != null) {
			return gasBooking;
		} else {
			throw new ResourceNotFoundException("Bookings on date:" + bookingDate + " not found!!!");
		}
	}

}

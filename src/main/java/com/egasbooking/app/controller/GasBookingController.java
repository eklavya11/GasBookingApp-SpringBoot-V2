package com.egasbooking.app.controller;

import java.text.ParseException;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egasbooking.app.dto.BookingResponseDto;
import com.egasbooking.app.dto.GasBookingDto;
import com.egasbooking.app.dto.GasBookingUpdateDto;
import com.egasbooking.app.dto.Response;
import com.egasbooking.app.entity.GasBooking;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.service.GasBookingService;

@RestController
@CrossOrigin(origins = "*")
public class GasBookingController {

	@Autowired
	private GasBookingService gasBookingService;

	@PostMapping({ "/customer/insert-gasbooking/{customerId}", "admin/insert-gasbooking/{customerId}" })
	public ResponseEntity<BookingResponseDto> insertGasBooking(@RequestBody @Valid GasBookingDto gasBookingDto,
			@PathVariable int customerId) throws CustomerException, ParseException {
		BookingResponseDto result = gasBookingService.insertGasBooking(gasBookingDto, customerId);
		if (result.isStatus()) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping({ "/customer/update-gasbooking/{gasBookingId}", "admin/update-gasbooking/{gasBookingId}" })
	public ResponseEntity<BookingResponseDto> updateGasbooking(
			@RequestBody @Valid GasBookingUpdateDto gasBookingUpdateDto, @PathVariable int gasBookingId)
			throws ResourceNotFoundException, CustomerException, ParseException {
		BookingResponseDto result = gasBookingService.updateGasBooking(gasBookingUpdateDto, gasBookingId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping({ "/customer/delete-gasbooking/{gasBookingId}", "admin/delete-gasbooking/{gasBookingId}" })
	public ResponseEntity<GasBooking> deleteGasbooking(@PathVariable int gasBookingId)
			throws ResourceNotFoundException {
		GasBooking result = gasBookingService.deleteGasBooking(gasBookingId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping({ "/customer/get-bill/{customerId}", "admin/get-bill/{customerId}" })
	public ResponseEntity<?> getBill(@PathVariable int customerId) throws CustomerException {

		return new ResponseEntity<>(gasBookingService.getBill(customerId), HttpStatus.OK);
	}

	@GetMapping({ "/customer/get-bookingDate" })
	public ResponseEntity<?> getBookingDate(@RequestParam String bookingDate) throws ResourceNotFoundException {

		Response response = new Response();
		LocalDate localDate = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			localDate = LocalDate.parse(bookingDate, formatter);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponse("Date should be yyyy-MM-dd");
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
			return new ResponseEntity<Response>(response, response.getStatusCode());
		}

	
		List<GasBooking> bookingDate2 = gasBookingService.getBookingDate(localDate);
		response.setResponse(bookingDate2);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		return new ResponseEntity<Response>(response, response.getStatusCode());

	}
}

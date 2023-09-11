package com.egasbooking.app.service;
 
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;



import com.egasbooking.app.dto.BookingResponseDto;
import com.egasbooking.app.dto.GasBookingDto;
import com.egasbooking.app.dto.GasBookingUpdateDto;
import com.egasbooking.app.entity.GasBooking;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
 
public interface GasBookingService {
 
   public BookingResponseDto insertGasBooking(GasBookingDto gasBookingDto, int customerId) throws CustomerException, ParseException;
 
   public GasBooking deleteGasBooking(int gasBookingId) throws ResourceNotFoundException;
 
   public List<GasBooking> getBill(int customerId) throws CustomerException;

   public  List<GasBooking> getBookingDate(LocalDate bookingDate) throws ResourceNotFoundException;

   public BookingResponseDto updateGasBooking( GasBookingUpdateDto gasBookingUpdateDto, int gasBookingId) throws ResourceNotFoundException, CustomerException, ParseException;

}
package com.egasbooking.app.service;

import java.time.LocalDate;
import java.util.List;

import com.egasbooking.app.dto.LoginDto;
import com.egasbooking.app.dto.LoginResultDto;
import com.egasbooking.app.entity.Admin;
import com.egasbooking.app.entity.GasBooking;
import com.egasbooking.app.exceptions.AdminException;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;

public interface AdminService {

	public Admin registerAdmin(Admin admin) throws AdminException;
	public Admin updateAdmin(Admin admin,int adminId) throws ResourceNotFoundException;
	public Admin deleteAdmin(int adminId) throws ResourceNotFoundException;
	public List<GasBooking> getAllBooking(int customerId) throws CustomerException;
	public List<GasBooking> getAllBookingForDays(LocalDate fromDate,LocalDate toDate) throws CustomerException;
	public LoginResultDto loginAdmin(LoginDto loginDto) throws CustomerException;
	public String login(LoginDto user)throws AdminException;

}

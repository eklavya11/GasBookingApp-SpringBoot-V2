package com.egasbooking.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egasbooking.app.dto.LoginDto;
import com.egasbooking.app.dto.LoginResultDto;
import com.egasbooking.app.entity.Admin;

import com.egasbooking.app.entity.GasBooking;
import com.egasbooking.app.exceptions.AdminException;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.repository.AdminRepo;

import com.egasbooking.app.repository.GasBookingRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private GasBookingRepository gasBookingRepository;

	

	@Override
	public Admin registerAdmin(Admin admin) throws AdminException{
		if(adminRepo.findByUsername(admin.getUsername())!=null) {
			throw new AdminException("Username already exists,enter another username");
		}
		if(adminRepo.findByMobileNumber(admin.getMobileNumber())!=null) {
			throw new AdminException("Mobile number already exists,enter another mobile number");
		}
		if(adminRepo.findByEmail(admin.getEmail())!=null) {
			throw new AdminException("Email already exists,enter another email");
		}
		return adminRepo.save(admin); 
	} 

	@Override
	public Admin updateAdmin(Admin admin, int adminId) throws ResourceNotFoundException {
		try {
			Admin result = adminRepo.findById(adminId).get();
			if (result != null && admin.getUserId() == adminId) {
				return adminRepo.save(admin);
			}
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Admin with id: " + adminId + " not found!!!");
		}
		return null;
	}

	@Override
	public Admin deleteAdmin(int adminId) throws ResourceNotFoundException {
		try {
			Admin admin = adminRepo.findById(adminId).get();
			if (admin != null) {
				adminRepo.deleteById(adminId);
				return admin;
			}
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Admin with id: " + adminId + " not found!!!");
		}
		return null;
	}

	@Override
	public List<GasBooking> getAllBooking(int customerId) throws CustomerException {
		List<GasBooking> gasBookings = gasBookingRepository.getAllBookingByCustomerId(customerId);
		if (gasBookings != null) {
			return gasBookings;
		} else {
			throw new CustomerException("Customer with customer id:" + customerId + " not found!!!");
		}
	}

	@Override
	public List<GasBooking> getAllBookingForDays(LocalDate fromDate, LocalDate toDate) throws CustomerException {
	    if (fromDate.compareTo(toDate) > 0) {
	        throw new CustomerException("From date cannot be greater than to date");
	    }
	    List<GasBooking> result = new ArrayList<>();
	    List<GasBooking> gasBookings = gasBookingRepository.findAll();
	    gasBookings.stream().forEach(g -> {
	        if (g.getBookingDate().compareTo(fromDate) >= 0 && g.getBookingDate().compareTo(toDate) <= 0) {
	            result.add(g);
	        }
	    });
	    return result;
	}
//	public List<GasBooking> getAllBookingForDays(LocalDate fromDate, LocalDate toDate) throws CustomerException {
//		List<GasBooking> result = new ArrayList<>();
//		List<GasBooking> gasBookings = gasBookingRepository.findAll();
//		gasBookings.stream().forEach(g -> {
//			if (g.getBookingDate().compareTo(fromDate) > 0 && g.getBookingDate().compareTo(toDate) < 0) {
//				result.add(g);
//			}
//		});
//		return result;
//	}

	@Override
	public LoginResultDto loginAdmin(LoginDto loginDto) throws CustomerException {
		LoginResultDto loginResultDto = new LoginResultDto();
		Admin admin = adminRepo.findByEmail(loginDto.getEmail());
		if (admin != null) {
			if (admin.getEmail().equals(loginDto.getEmail())
					&& admin.getPassword().equals(loginDto.getPassword())) {
				loginResultDto.setId(admin.getUserId());
				loginResultDto.setLoginMessage("SUCCESSFULY LOGIN!!!");
				loginResultDto.setEmail(loginDto.getEmail());
				return loginResultDto;
			} else {
				throw new CustomerException("Customer with username:" + loginDto.getEmail() + " not found!!!");
			}
		} else {
			loginResultDto.setLoginMessage("Invalid User");
			
		}
		return loginResultDto;
	}

	@Override
	public String login(LoginDto loginDto) throws AdminException {
		
		Admin admin = adminRepo.findByEmail(loginDto.getEmail());
		if(admin==null)
			throw new AdminException("Admin User does't exists with given email/user id.");

		if (!admin.getUsername().equals(loginDto.getEmail()) && !admin.getPassword().equals(loginDto.getPassword()))
			throw new AdminException("Password and UserName does't match ");

		String issuer = admin.getEmail();
		Date expiry = new Date(System.currentTimeMillis() + (60 * 1000));

		return  Jwts.builder().setIssuer(issuer).setExpiration(expiry)
				.signWith(SignatureAlgorithm.HS512, "Secret123").compact();
	}
}

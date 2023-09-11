package com.egasbooking.app.controller;


import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egasbooking.app.dto.LoginDto;
import com.egasbooking.app.dto.LoginResultDto;
import com.egasbooking.app.dto.Response;
import com.egasbooking.app.entity.Admin;
import com.egasbooking.app.entity.Customer;
import com.egasbooking.app.entity.GasBooking;
import com.egasbooking.app.exceptions.AdminException;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.service.AdminService;
import com.egasbooking.app.service.CustomerService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;
	private CustomerService customerService;
	@PostMapping("/registeradmin")
	ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) throws AdminException{
		Admin result = adminService.registerAdmin(admin);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update-admin/{adminId}")
	ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @PathVariable int adminId)
			throws ResourceNotFoundException {
		Admin result = adminService.updateAdmin(admin, adminId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete-admin/{adminId}")
	ResponseEntity<?> deleteAdmin(@PathVariable int adminId) throws ResourceNotFoundException {
		adminService.deleteAdmin(adminId);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}

	@GetMapping("admin/getAllBookingForDays")
	public ResponseEntity<?> getAllBookingForDays(@RequestParam String fromDate, @RequestParam String toDate)
			throws CustomerException {
		return ResponseEntity.ok(adminService.getAllBookingForDays(LocalDate.parse(fromDate), LocalDate.parse(toDate)));
	}

	@GetMapping("/get-all-booking/{customerId}")
	public ResponseEntity<?> getAllBooking(@PathVariable int customerId) throws CustomerException {
		List<GasBooking> result = adminService.getAllBooking(customerId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/Customers")
	public List<Customer> viewCustomers(@CookieValue("jwt") String jwt) throws AdminException {
		
		if(jwt == null)
			throw new AdminException("Unauthenticated !");
	
		try {
		Claims claim = Jwts.parser().setSigningKey("Secret123").parseClaimsJws(jwt).getBody();
		String email = claim.getIssuer();
		
		}
		catch(ExpiredJwtException e) {
			throw new AdminException("JWT got expired please try re loging. ");
		}
		 
		catch (Exception e) {
			throw new AdminException("Unauthenticated !");
		}
		
		return customerService.viewCustomers();
	}
	@PostMapping("login")
	public String login(@RequestBody LoginDto user, HttpServletResponse response) throws AdminException {

		Cookie cookie = new Cookie("jwt", adminService.login(user));
		response.addCookie(cookie);
		return "Login Success !"; 
	}
	
	@PostMapping("logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("jwt", "");
		response.addCookie(cookie);
		return "Logout Success.";
	}

	@PostMapping("validate/admin/login")
	public ResponseEntity<Response> loginAdmin(@RequestBody LoginDto LoginDto)  {

		Response response = new Response();
		try {
			LoginResultDto validateUserLogin = adminService.loginAdmin(LoginDto);
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
			response.setResponse(validateUserLogin);
			return new ResponseEntity<Response>(response, response.getStatusCode());

		} catch (Exception e) {
			response.setStatus(Response.FAILURE);
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponse("LOGIN FAIL");
			return new ResponseEntity<Response>(response, response.getStatusCode());
		}

	}
}

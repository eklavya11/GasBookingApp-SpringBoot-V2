package com.egasbooking.app.controller;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.egasbooking.app.dto.LoginDto;
import com.egasbooking.app.dto.LoginResultDto;
import com.egasbooking.app.dto.Response;

import com.egasbooking.app.entity.Customer;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.service.CustomerService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer/insert-customer")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Customer> insertCustomer(@RequestBody @Valid Customer Customer) throws CustomerException{
		Customer result = customerService.insertCustomer(Customer);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/customer/update-customer/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid Customer customer,@PathVariable int customerId) throws CustomerException{
		Customer result = customerService.updateCustomer(customer, customerId);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/admin/delete-customer/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) throws CustomerException{
		Customer result = customerService.deleteCustomer(customerId);
		if(result != null) {
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/admin/view-all-customers")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> result = customerService.viewCustomers();
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/admin/view-customer-by-id/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) throws CustomerException{
		Customer result = customerService.viewCustomerById(customerId);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll/customer")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<List<Customer>> getAllCustomerList()  {
		List<Customer> AllCustomerList = customerService.getAllCustomerList();
		return new ResponseEntity<>(AllCustomerList, HttpStatus.OK);
	}
	
	@PostMapping("/validate/customer/login")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Response> validateCustomer(@RequestBody LoginDto LoginDto)  {

		Response response = new Response();
		try {
			LoginResultDto validateUserLogin = customerService.validateCustomer(LoginDto);
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
	
	@GetMapping("/Customers")
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<Customer> viewCustomers(@CookieValue("jwt") String jwt) throws CustomerException {
		
		if(jwt == null)
			throw new CustomerException("Unauthenticated !");
	
		try {
		Claims claim = Jwts.parser().setSigningKey("Secret123").parseClaimsJws(jwt).getBody();
		String email = claim.getIssuer();
		
		}
		catch(ExpiredJwtException e) {
			throw new CustomerException("JWT got expired please try re loging. ");
		}
		 
		catch (Exception e) {
			throw new CustomerException("Unauthenticated !");
		}
		
		return customerService.viewCustomers();
	}
	
	
	
	@PostMapping("logout")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("jwt", "");
		response.addCookie(cookie);
		return "Logout Success.";
	}
	@PostMapping("login")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Response> loginTest(@RequestBody LoginDto user, HttpServletResponse response)throws CustomerException{
		Response res = new Response();
		try {
			LoginResultDto validateUserLogin = customerService.validateCustomer(user);
			res.setStatus(Response.SUCCESS);
			res.setStatusCode(HttpStatus.OK);
			res.setResponse(validateUserLogin);
			Cookie cookie = new Cookie("jwt", customerService.login(user));
			response.addCookie(cookie);
			return new ResponseEntity<Response>(res, res.getStatusCode());

		} catch (Exception e) {
			res.setStatus(Response.FAILURE);
			res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setResponse("LOGIN FAIL");
			return new ResponseEntity<Response>(res, res.getStatusCode());
		}
	}
	
	
	
}

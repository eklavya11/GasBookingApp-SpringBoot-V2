package com.egasbooking.app.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egasbooking.app.dto.LoginDto;
import com.egasbooking.app.dto.LoginResultDto;
import com.egasbooking.app.entity.Bank;
import com.egasbooking.app.entity.Customer;
import com.egasbooking.app.entity.Cylinder;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.repository.BankRepository;
import com.egasbooking.app.repository.CustomerRepository;
import com.egasbooking.app.repository.CylinderRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private CylinderRepository cylinderRepository;
	@Override
	public Customer insertCustomer(Customer customer) throws CustomerException {
		
		if(customerRepository.findByUsername(customer.getUsername())!=null) {
			throw new CustomerException("Username already exists,enter another username");
		}
		
		
		if(customerRepository.findByEmail(customer.getEmail())!=null) {
			throw new CustomerException("Email already exists,enter another email");
		}
		
		
		
		Cylinder cylinder = customer.getCylinder();
		Bank bank = customer.getBank();
		if(bankRepository.findByAccountNo(bank.getAccountNo())!=null){
			throw new CustomerException("Provide a unique account number");
		}
		Bank savedb = bankRepository.save(bank);
		Cylinder cylinderdb = cylinderRepository.save(cylinder);
		customer.setBank(savedb);
		customer.setCylinder(cylinderdb);

	  return customerRepository.save(customer);
	  
	}

	@Override
	public Customer updateCustomer(Customer customer, int customerId) throws CustomerException {
		try {
			Customer result = customerRepository.findById(customerId).get();
			if (result != null) {
				return customerRepository.save(customer);
			}
		} catch (NoSuchElementException e) {
			throw new CustomerException("Customer with customer id:" + customerId + " not found!!!");
		}
		return null;
	}

	@Override
	public Customer deleteCustomer(int customerId) throws CustomerException {
		try {
			Customer result = customerRepository.findById(customerId).get();
			if (result != null) {
				customerRepository.delete(result);
				return result;
			}
		} catch (NoSuchElementException e) {
			throw new CustomerException("Customer with customer id:" + customerId + " not found!!!");
		}
		return null;
	}

	@Override
	public List<Customer> viewCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer viewCustomerById(int customerId) throws CustomerException {
		try {
			Customer result = customerRepository.findById(customerId).get();
			if (result != null) {
				return result;
			}
		} catch (NoSuchElementException e) {
			throw new CustomerException("Customer with customer id:" + customerId + " not found!!!");
		}
		return null;
	}

	@Override
	public LoginResultDto validateCustomer(LoginDto loginDto) throws CustomerException {
		LoginResultDto loginResultDto = new LoginResultDto();
		Customer customer = customerRepository.findByEmail(loginDto.getEmail());
		if (customer != null) {
			if (customer.getEmail().equals(loginDto.getEmail())
					&& customer.getPassword().equals(loginDto.getPassword())) {
				loginResultDto.setId(customer.getUserId());
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
	public List<Customer> getAllCustomerList() {
		return customerRepository.findAll();
		
	}

	@Override
	public String login(LoginDto loginDto) throws CustomerException {
	    Customer customer = customerRepository.findByEmail(loginDto.getEmail());
		if(customer==null)
			throw new CustomerException("Customer does't exists with given email/user id.");

		if (!customer.getUsername().equals(loginDto.getEmail()) && !customer.getPassword().equals(loginDto.getPassword()))
			throw new CustomerException("Password and UserName does't match ");

		String issuer = customer.getEmail();
		Date expiry = new Date(System.currentTimeMillis() + (60 * 1000));

		return  Jwts.builder().setIssuer(issuer).setExpiration(expiry)
				.signWith(SignatureAlgorithm.HS512, "Secret123").compact();
	}

}

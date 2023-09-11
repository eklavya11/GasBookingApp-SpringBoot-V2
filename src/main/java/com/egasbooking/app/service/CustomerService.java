package com.egasbooking.app.service;

import java.util.List;

import com.egasbooking.app.dto.LoginDto;
import com.egasbooking.app.dto.LoginResultDto;
import com.egasbooking.app.entity.Customer;
import com.egasbooking.app.exceptions.CustomerException;

public interface CustomerService {
	
	public Customer deleteCustomer(int customerId) throws CustomerException;
	public List<Customer> viewCustomers();
	public Customer viewCustomerById(int customerId) throws CustomerException;
	public LoginResultDto validateCustomer(LoginDto loginDto) throws CustomerException;
	public List<Customer> getAllCustomerList();
	public Customer insertCustomer( Customer customer) throws CustomerException;
	public Customer updateCustomer(Customer customer, int customerId) throws CustomerException;
	public String login(LoginDto loginDto)throws CustomerException;

}

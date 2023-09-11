package com.egasbooking.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egasbooking.app.entity.Customer;

import com.egasbooking.app.entity.SurrenderCylinder;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.repository.CustomerRepository;
import com.egasbooking.app.repository.SurrenderCylinderRepo;

@Service
public class SurrenderCylinderServiceImpl implements SurrenderCylinderService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SurrenderCylinderRepo surrenderCylinderRepo;

	@Override
	public SurrenderCylinder insertSurrenderCylinder(int customerId) throws CustomerException {
		SurrenderCylinder surrenderCylinder = new SurrenderCylinder();
		try {
			Customer customer = customerRepository.findById(customerId).get();
			if (customer != null) {
				surrenderCylinder.setCustomer(customer);
				surrenderCylinder.setSurrendarDate(LocalDate.now());
				surrenderCylinder.setCylinder(customer.getCylinder());
				return surrenderCylinderRepo.save(surrenderCylinder);
			}
		} catch (NoSuchElementException e) {
			throw new CustomerException("Customer With This id: " + customerId + " does not exist");
		}
		return null;
	}

	@Override
	public SurrenderCylinder deleteSurrenderCylinder(int surrenderCylinderId) throws ResourceNotFoundException {
		SurrenderCylinder result = surrenderCylinderRepo.findById(surrenderCylinderId).get();
		if (result != null) {
			surrenderCylinderRepo.delete(result);
			return result;
		} else {
			throw new ResourceNotFoundException("Cylinder with this id :" + surrenderCylinderId + " not found!!!");
		}
	}


	@Override
	public Optional<SurrenderCylinder> getSurrenderCylinder(int surrenderId) throws ResourceNotFoundException {
		
		return surrenderCylinderRepo.findById(surrenderId);
	}

	@Override
	public List<SurrenderCylinder> getSurrenderCylinderByDate(LocalDate surrenderDate)
			throws ResourceNotFoundException {
		List<SurrenderCylinder> cylinders = surrenderCylinderRepo.findBySurrenderDate(surrenderDate);
		if (cylinders != null) {
			return cylinders;
		} else {
			throw new ResourceNotFoundException("Cylinders on this date: " + surrenderDate + " not found");
		}
	}
}
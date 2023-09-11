package com.egasbooking.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.egasbooking.app.entity.SurrenderCylinder;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;

public interface SurrenderCylinderService {
	SurrenderCylinder insertSurrenderCylinder(int customerId) throws CustomerException;


	SurrenderCylinder deleteSurrenderCylinder(int surrenderCylinderId) throws ResourceNotFoundException;


	Optional<SurrenderCylinder> getSurrenderCylinder(int surrenderId) throws ResourceNotFoundException;

	List<SurrenderCylinder> getSurrenderCylinderByDate(LocalDate localDate1) throws ResourceNotFoundException;
}

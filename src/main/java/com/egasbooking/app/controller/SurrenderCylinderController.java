package com.egasbooking.app.controller;


import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egasbooking.app.dto.Response;


import com.egasbooking.app.entity.SurrenderCylinder;
import com.egasbooking.app.exceptions.CustomerException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.service.SurrenderCylinderService;

@RestController
@CrossOrigin(origins = "*")
public class SurrenderCylinderController {

	@Autowired
	private SurrenderCylinderService surrenderCylinderService;

//    
	@PostMapping({ "/customer/insert-surrender-cylinder/{customerId}" })
	public ResponseEntity<SurrenderCylinder> insertSurrenderCylinder(
			@PathVariable int customerId) throws CustomerException {
		SurrenderCylinder result = surrenderCylinderService.insertSurrenderCylinder(customerId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


	@DeleteMapping({ "/admin/delete-surrender-cylinder/{surrenderCylinderId}" })
	public ResponseEntity<SurrenderCylinder> deleteSurrenderCylinder(@PathVariable int surrenderCylinderId)
			throws ResourceNotFoundException {
		SurrenderCylinder result = surrenderCylinderService.deleteSurrenderCylinder(surrenderCylinderId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("get-surrender-cylinder-by-id/{surrenderId}")
	public ResponseEntity<?> getSurrenderCylinder(@PathVariable int surrenderId) throws ResourceNotFoundException {
		return ResponseEntity.ok(surrenderCylinderService.getSurrenderCylinder(surrenderId));
	}

	@GetMapping({ "/customer/get-getSurrender" })
	public ResponseEntity<?> getSurrenderCylinderByDate(@RequestParam String surrenderDate)
			throws ResourceNotFoundException {

		Response response = new Response();
		LocalDate localDate = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			localDate = LocalDate.parse(surrenderDate, formatter);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponse("Date should be yyyy-mm-dd");
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
			return new ResponseEntity<Response>(response, response.getStatusCode());
		}

		List<SurrenderCylinder> SurrenderCylinderlist = surrenderCylinderService.getSurrenderCylinderByDate(localDate);
		response.setResponse(SurrenderCylinderlist);
		response.setStatus(Response.SUCCESS);
		response.setStatusCode(HttpStatus.OK);
		return new ResponseEntity<Response>(response, response.getStatusCode());

	}

}

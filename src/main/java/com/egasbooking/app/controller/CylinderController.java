package com.egasbooking.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.egasbooking.app.entity.Cylinder;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.service.CylinderService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class CylinderController {

	@Autowired
	private CylinderService cylinderService;

	@PostMapping("/insert-cylinder")
	public ResponseEntity<Cylinder> insertCylinder(@RequestBody Cylinder cylinder) throws ResourceNotFoundException {
		Cylinder result = cylinderService.insertCylinder(cylinder);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update-cylinder/{cylinderId}")
	public ResponseEntity<Cylinder> updateCylinder(@RequestBody Cylinder cylinder, @PathVariable int cylinderId)
			throws ResourceNotFoundException {
		Cylinder result = cylinderService.updateCylinder(cylinder, cylinderId);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete-cylinder/{cylinderId}")
	public ResponseEntity<?> deleteCylinder(@PathVariable int cylinderId) throws ResourceNotFoundException {
		Cylinder result = cylinderService.deleteCylinder(cylinderId);
		if (result != null) {
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/view-cylinder-by-type/{type}")
	public ResponseEntity<List<Cylinder>> viewCylinderByType(@PathVariable String type)
			throws ResourceNotFoundException {
		List<Cylinder> result = cylinderService.viewCylinderByType(type);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/view-cylinder-by-weight/{weight}")
	public ResponseEntity<List<Cylinder>> viewCylinderByWeight(@PathVariable float weight)
			throws ResourceNotFoundException {
		List<Cylinder> result = cylinderService.viewCylinderByWeight(weight);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/view-cylinder-by-strapColor/{strapColor}")
	public ResponseEntity<List<Cylinder>> viewCylinderByStrapColor(@PathVariable String strapColor)
			throws ResourceNotFoundException {
		List<Cylinder> result = cylinderService.viewCylinderByStrapColor(strapColor);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll/cylinder")
	public ResponseEntity<List<Cylinder>> getAllCylinderList()  {
		List<Cylinder> AllCustomerList = cylinderService.getAllCylinderList();
		return new ResponseEntity<>(AllCustomerList, HttpStatus.OK);
	}
}

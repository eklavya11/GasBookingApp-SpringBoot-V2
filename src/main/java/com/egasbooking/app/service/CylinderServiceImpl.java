package com.egasbooking.app.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egasbooking.app.entity.Cylinder;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.repository.CylinderRepository;

@Service
public class CylinderServiceImpl implements CylinderService {

	@Autowired
	private CylinderRepository cylinderRepository;

	@Override
	public Cylinder insertCylinder(Cylinder cylinder) throws ResourceNotFoundException {
		if (cylinder != null) {
			Optional<Cylinder> result = cylinderRepository.findById(cylinder.getCylinderId());
			if (result.isPresent()) {
				throw new ResourceNotFoundException("User already Exists with id " + cylinder.getCylinderId());
			}
		}
		return cylinderRepository.save(cylinder);
	}

	@Override
	public Cylinder updateCylinder(Cylinder cylinder, int cylinderId) throws ResourceNotFoundException {
		try {
			Cylinder result = cylinderRepository.findById(cylinderId).get();
			if (result != null) {
				return cylinderRepository.save(cylinder);
			}
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Cylinder with this id: " + cylinderId + "not found!!!");
		}
		return null;
	}

	@Override
	public Cylinder deleteCylinder(int cylinderId) throws ResourceNotFoundException {
		try {
			Cylinder result = cylinderRepository.findById(cylinderId).get();
			if (result != null) {
				cylinderRepository.delete(result);
				return result;
			}
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Cylinder with this id: " + cylinderId + "not found!!!");
		}
		return null;
	}

	@Override
	public List<Cylinder> viewCylinderByWeight(float weight) throws ResourceNotFoundException {
		List<Cylinder> cylinders = cylinderRepository.findByWeight(weight);
		if (cylinders != null) {
			return cylinders;
		} else {
			throw new ResourceNotFoundException("Cylinder of this type: " + weight + " not found");
		}
	}

	@Override
	public List<Cylinder> viewCylinderByType(String type) throws ResourceNotFoundException {
		List<Cylinder> cylinders = cylinderRepository.findByType(type);
		if (cylinders != null) {
			return cylinders;
		} else {
			throw new ResourceNotFoundException("Cylinder of this type: " + type + " not found");
		}
	}

	@Override
	public List<Cylinder> viewCylinderByStrapColor(String strapColor) throws ResourceNotFoundException {
		List<Cylinder> cylinders = cylinderRepository.findByStrapColor(strapColor);
		if (cylinders != null) {
			return cylinders;
		} else {
			throw new ResourceNotFoundException("Cylinder of this type: " + strapColor + " not found");
		}
	}

	@Override
	public List<Cylinder> getAllCylinderList() {
		return cylinderRepository.findAll();
		
	}
}

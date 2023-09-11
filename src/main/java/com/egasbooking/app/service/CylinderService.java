package com.egasbooking.app.service;

import java.util.List;

import com.egasbooking.app.entity.Cylinder;
import com.egasbooking.app.exceptions.ResourceNotFoundException;

public interface CylinderService {

	public Cylinder insertCylinder(Cylinder cylinder) throws ResourceNotFoundException;
	public Cylinder updateCylinder(Cylinder cylinder,int cylinderId) throws ResourceNotFoundException;
	public Cylinder deleteCylinder(int cylinderId) throws ResourceNotFoundException;
	public List<Cylinder> viewCylinderByType(String type) throws ResourceNotFoundException;
	public List<Cylinder> viewCylinderByWeight(float weight) throws ResourceNotFoundException;
	public List<Cylinder> viewCylinderByStrapColor(String strapColor) throws ResourceNotFoundException;
	public List<Cylinder> getAllCylinderList();
}

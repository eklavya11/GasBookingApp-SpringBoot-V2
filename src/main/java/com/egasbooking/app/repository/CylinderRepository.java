package com.egasbooking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egasbooking.app.entity.Cylinder;

/*
 * CylinderRepository interface for creating different methods of CRUD
 */

@Repository
public interface CylinderRepository extends JpaRepository<Cylinder, Integer> {

	List<Cylinder> findByType(String type);
	List<Cylinder> findByWeight(float weight);
	List<Cylinder> findByStrapColor(String strapColor);
}

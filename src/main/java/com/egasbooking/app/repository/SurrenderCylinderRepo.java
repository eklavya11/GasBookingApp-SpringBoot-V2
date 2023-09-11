package com.egasbooking.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.egasbooking.app.entity.SurrenderCylinder;

@Repository
public interface SurrenderCylinderRepo extends JpaRepository<SurrenderCylinder, Integer> {

	@Query(value = "SELECT * FROM surrender_cylinder g "
			+ " WHERE  g.surrender_date=:surrenderDate ", nativeQuery = true)
	List<SurrenderCylinder> findBySurrenderDate(LocalDate surrenderDate);


}

package com.egasbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egasbooking.app.entity.Admin;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

	Admin findByUsername(String userName);
	Admin findByEmail(String email);
	Admin findByMobileNumber(String mobileNumber);


}

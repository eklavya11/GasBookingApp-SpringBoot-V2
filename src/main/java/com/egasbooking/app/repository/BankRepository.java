package com.egasbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egasbooking.app.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

	Bank findByAccountNo(String accountno);
}

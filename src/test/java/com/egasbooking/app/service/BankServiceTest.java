//package com.egasbooking.app.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.egasbooking.app.entity.Bank;
//import com.egasbooking.app.entity.Cylinder;
//import com.egasbooking.app.exceptions.ResourceNotFoundException;
//import com.egasbooking.app.repository.BankRepository;
//import com.egasbooking.app.repository.CylinderRepository;
//import com.egasbooking.app.service.BankService;
//
//@SpringBootTest
//public class BankServiceTest {
//	
////	@Autowired
////	private BankService bankService;
////	@Autowired
////	private BankRepository bankRepository;
////	
////	 @Test
////	    void testInsertBank() throws ResourceNotFoundException {
////	   // Bank bank = new Bank(8,"ICICI","Bngl",7895, "SP09786YTYT");
////	    Bank bank = new Bank(0,234567888,"SBIN0004798","ICICI","ABC1","CDE1","Bngl","MH","203040", "INDIA");
////	    bankRepository.save(bank);
////	    }
////	@Test
////	void testUpdateBank() throws ResourceNotFoundException {
////	    Bank bank = new Bank(1,234567889,"SBIN0004799","IDBI","ABC1","CDE1","Bngl","MH","404040", "INDIA");
////	    bankRepository.save(bank);
////		bankRepository.save(bank);
////
////	}
//
////    @Test
////    void testDeleteBank() throws ResourceNotFoundException {
////    	 Bank bank = new Bank(1,234567889,"SBIN0004799","IDBI","ABC1","CDE1","Bngl","MH","404040", "INDIA");
////    bankRepository.delete(bank);
////    }
//
//}

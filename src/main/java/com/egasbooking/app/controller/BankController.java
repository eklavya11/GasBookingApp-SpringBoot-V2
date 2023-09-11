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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.egasbooking.app.entity.Bank;
import com.egasbooking.app.exceptions.BankException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.service.BankService;

@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = "*")
public class BankController {

	@Autowired
	private BankService bankService;

	@PostMapping("/insertBank")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Bank> insertBank(@RequestBody Bank bank) {
		Bank result = bankService.insertBank(bank);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateBank")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Bank> updateBank(@RequestBody Bank bank) {
		Bank result = bankService.updateBank(bank);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteBank/{bankId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> deleteBank(@PathVariable int bankId) throws BankException {
		Bank result = bankService.deleteBank(bankId);
		if (result != null) {
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/bankList")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<List<Bank>> bankList()  {
		List<Bank> bankList = bankService.bankList();
		return new ResponseEntity<>(bankList, HttpStatus.OK);
	}



}

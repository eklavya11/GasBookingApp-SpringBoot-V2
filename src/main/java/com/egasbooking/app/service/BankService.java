package com.egasbooking.app.service;

import java.util.List;

import com.egasbooking.app.entity.Bank;
import com.egasbooking.app.exceptions.BankException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;

public interface BankService {

	public Bank insertBank(Bank bank);
	public Bank updateBank(Bank bank);
	public Bank deleteBank(int bankId) throws BankException;
	public List<Bank> bankList();
	
}

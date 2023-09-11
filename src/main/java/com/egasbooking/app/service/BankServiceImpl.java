package com.egasbooking.app.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egasbooking.app.entity.Bank;
import com.egasbooking.app.exceptions.BankException;
import com.egasbooking.app.exceptions.ResourceNotFoundException;
import com.egasbooking.app.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;

	@Override
	public Bank insertBank(Bank bank) {
		return bankRepository.save(bank);
	}
	@Override
	public Bank updateBank(Bank bank) {
		bankRepository.save(bank);
		return bank;
	}

	@Override
	public Bank deleteBank(int bankId) throws BankException{
		Bank bank = bankRepository.findById(bankId).get();
		if (bank.getBankId() != 0) {
			bankRepository.deleteById(bank.getBankId());
			return bank;
		} else {
			throw new BankException("Route with id " + bankId + " not found!!");
		}
	}

	@Override
	public List<Bank> bankList() {
		return bankRepository.findAll();
		
	}



	

}

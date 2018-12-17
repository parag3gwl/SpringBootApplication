package com.parag.account.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.parag.account.model.Account;
import com.parag.account.repository.AccountDAO;


//Service class
@Service
public class AccountService {
	@Autowired
	AccountDAO repository;

	// Creating new Account
	public void createAccount(Account ac) throws Exception {
		Boolean res = repository.existsById(ac.getAccountNo());
		if (res == false) {
			repository.save(ac);
		} else {
			System.out.println("\n Account already exist");
			throw new HTTPException(HttpStatus.CONFLICT.value());
		}
	}

	//Create multiple Accounts
	public void createMultipleAccounts(ArrayList<Account> ac) throws Exception {
		repository.saveAll(ac);
	}

	// Get account details for particular account ID
	public Account getAccount(String accountNo) {
		Optional<Account> repoAccount = repository.findById(accountNo);
		Account result = new Account();
		if (repoAccount.isPresent()){
			result = repoAccount.get();
		}else{
			System.out.println("\n Account does not exist");
			throw new HTTPException(HttpStatus.NOT_FOUND.value());
		}
		return result;
	}
	
	//Get All account Details
	public ArrayList<Account> getAccounts() {
		ArrayList<Account> repoAccount = (ArrayList<Account>) repository.findAll();
		if(repoAccount.size()!=0){
			return repoAccount;
		}else{
			System.out.println("\n Empty");
			throw new HTTPException(HttpStatus.NOT_FOUND.value());
		}
	}
	
	//Update an exiting account
	public void updateAccount(Account ac) {
		Boolean res = repository.existsById(ac.getAccountNo());
		if (res == true) {
			repository.save(ac);
		}
		System.out.println("\n Account does not exist");
		throw new HTTPException(HttpStatus.NOT_MODIFIED.value());
	}

	//Delete an existing account
	public void deleteAccount(String accountNo) {
		Boolean res = repository.existsById(accountNo);
		if (res == true){
			repository.deleteById(accountNo);
		}else{
			System.out.println("\n Account does not exist");
			throw new HTTPException(HttpStatus.NOT_FOUND.value());
		}
	}

	//Delete All Accounts
	public void deleteAllAccounts() {
		ArrayList<Account> repoAccount = (ArrayList<Account>) repository.findAll();
		if(repoAccount.size()!=0){
			repository.deleteAll();
		}
		else{
			throw new HTTPException(HttpStatus.NOT_FOUND.value());
		}
	}
}
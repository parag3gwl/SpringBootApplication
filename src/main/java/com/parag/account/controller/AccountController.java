package com.parag.account.controller;

import java.util.ArrayList;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.parag.account.model.Account;
import com.parag.account.service.AccountService;

/**
* The AccountController program implements all the endpoints
* which will performs CRUD operations on ACcount Database
* @author  Parag Mangal
* @since   2018-04-30 
*/
@RestController
public class AccountController {
	@Autowired
	AccountService accService;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/Hello")
	public String firstMethod() {
		return "Hello";
	}
	
	
	/**
	* createAccount Method is used for Creating new account.
	* @param  ac This is the parameter to createAccount
	* @since   2018-04-23
	* @return HTTP Status code
	*/
	@RequestMapping(method = RequestMethod.PUT, value = "/createAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createAccount(@RequestBody Account ac) {
		System.out.println("AccountNumber:" + ac);
		if(ac.getAccountNo()==null||ac.getAccountNo().isEmpty()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			accService.createAccount(ac);
			//response code -CREATED: Request come to the server and server created the account. 
			return new ResponseEntity<>(HttpStatus.CREATED);
		} 
		catch(Exception e){
			//Response code- INTERNAL_SERVER_ERROR: request came to the server for creating the account but Internal server error occurred.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	* createMultipleAccounts Method is used for Creating Multiple account.
	* @param  ac This is the parameter to createMultipleAccounts
	* @since   2018-04-23
	* @return HTTP Status code
	*/
	
	@RequestMapping(method = RequestMethod.PUT, value = "/createMultipleAccounts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createMultipleAccounts(@RequestBody ArrayList<Account> ac) {
		System.out.println("AccountNumber:" + ac);
		if(ac.isEmpty()){
			//Response code- BAD_REQUEST: Client doesn't pass the required parameter
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			accService.createMultipleAccounts(ac);
			//response code -CREATED: Request come to the server and server created All the Accounts.
			return new ResponseEntity<>(HttpStatus.CREATED);
		} 
		catch(Exception e){
			//Response code-INTERNAL_SERVER_ERROR: request came to the server for creating multiple accounts but Internal server error occurred.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	* getAccount Method is used to get account details for specific AccountID
	* @param  accountNo This is the parameter to getAccount
	* @since   2018-04-24
	* @return HTTP Status code
	*/
	@RequestMapping("/getAccount")
	public ResponseEntity<Account> getAccount(String accountNo) {
		System.out.println("AccountNumber:" + accountNo);
		if(accountNo==null||accountNo.isEmpty()){
			//Response code- BAD_REQUEST: Client doesn't pass the required parameter
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try{
			Account result = accService.getAccount(accountNo);
			//response code -OK: Request come to the server and server provided the account details.
			return new ResponseEntity<Account>(result, HttpStatus.OK);
		}
		catch(HTTPException e){
			//Response code - NOT_FOUND: Request came to the server and server unable to find account in database for given account number
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch(Exception e){
			//Response code-INTERNAL_SERVER_ERROR: request came to the server but Internal server error occurred.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	/**
	* getAccounts Method is used to get All account details from Account Table
	* @since   2018-04-24
	* @return HTTP Status code
	*/
	@RequestMapping("/getAccounts")
	public ResponseEntity<ArrayList<Account>> getAccounts() {
		try{
			ArrayList<Account> result = accService.getAccounts();
			//response code -OK: Request come to the server and server provided the account details.			
			return new ResponseEntity<ArrayList<Account>>(result, HttpStatus.OK);
		}
		catch(HTTPException e){
			//Response code - NOT_FOUND: Request came to the server and server unable to find any account in database
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch(Exception e){
			//Response code-INTERNAL_SERVER_ERROR: request came to the server but Internal server error occurred.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	/**
	* updateAccount Method is used to update the existing account details for specific AccountID
	* @param  ac This is the parameter to updateAccount
	* @since   2018-04-26
	* @return HTTP Status code
	*/
	@RequestMapping(method = RequestMethod.PUT, value = "/updateAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAccount(@RequestBody Account ac) {
		System.out.println("AccountNumber:" + ac);
		if(ac.getAccountNo()==null||ac.getAccountNo().isEmpty()){
			//Response code- BAD_REQUEST: Client doesn't pass the required parameter
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			 accService.updateAccount(ac);
				//response code -OK: Request come to the server and server updated the account details.
			 return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch(HTTPException e){
			//Response code - NOT_FOUND: Request came to the server and server unable to the account in database
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		catch (Exception e) {
			//Response code-INTERNAL_SERVER_ERROR: request came to the server but Internal server error occurred.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	/**
	* deleteAccount Method is used to delete the existing account details for specific AccountID
	* @param  accountNo This is the parameter to deleteAccount
	* @since   2018-04-26
	* @return HTTP Status code
	*/
	@RequestMapping("/deleteAccount")
	public ResponseEntity<Account> deleteAccount(String accountNo) {
		System.out.println("AccountNumber:" + accountNo);
		if(accountNo==null||accountNo.isEmpty()){
			//Response code- BAD_REQUEST: Client doesn't pass the required parameter
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try{
			accService.deleteAccount(accountNo);
			//Response code - NO_CONTENT: Request came to sever and server deleted the account
			return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
		}
		catch(HTTPException e){
			//Response code - NOT_FOUND: Request came to the server and server unable to find the account in database
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch(Exception e){
			//Response code-INTERNAL_SERVER_ERROR: If Internal server error occurred.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	/**
	* deleteAllAccounts Method is used to delete all the Accounts form Account database
	* @since   2018-04-26
	* @return HTTP Status code
	*/
	@RequestMapping("/deleteAllAccounts")
	public ResponseEntity<Object> deleteAllAccounts() {
		try{
			accService.deleteAllAccounts();
			//Response code - NO_CONTENT: Request came to sever and server deleted all the accounts.
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		catch(HTTPException e){
			//Response code - NOT_FOUND: Request came to the server and server unable to find any account in database
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch(Exception e){
			///Response code-INTERNAL_SERVER_ERROR: Request came to the server but Internal server error occurred.
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}

package com.parag.account.service;
import java.util.Date;
import java.util.Optional;

import javax.xml.ws.http.HTTPException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.parag.account.model.Account;
import com.parag.account.repository.AccountDAO;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	
	@InjectMocks
	AccountService accService;
	
	@Mock
	AccountDAO repository;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void getAccountTest() {
		Account expected = new Account();
		expected.setAccountName("Salary Account");
		expected.setAccountNo("1");
		expected.setAge(32);
		expected.setBirthday(new Date(1986,11,26));
		expected.setIsActive(true);
		
		
		Mockito.when(repository.findById("1")).thenReturn(Optional.of(expected));
		Account a = accService.getAccount("1");
		Assert.assertSame(expected, a);
	}
	
	@Test
	public void getAccountNotFoundTest() throws HTTPException {
		exception.expect(HTTPException.class);
		Account a = accService.getAccount("2");	
	}

}

package com.testing.piggybank.account;

import com.testing.piggybank.model.Account;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AccountControllerTest {

	@Mock
	private AccountService accountService;

	@InjectMocks
	private AccountController accountController;

	public AccountControllerTest() {

		MockitoAnnotations.openMocks(this);

	}

	@Test
	void testGetAccount_Success() {

		// Arrange
		long accountId = 1L;
		Account mockAccount = new Account();
		mockAccount.setId(accountId);
		mockAccount.setName("Savings Account");
		mockAccount.setBalance(1000.0);
		when(accountService.getAccount(accountId)).thenReturn(Optional.of(mockAccount));

		// Act
		ResponseEntity<AccountResponse> response = accountController.getAccount(accountId);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		AccountResponse responseBody = response.getBody();
		assertEquals(mockAccount.getId(), responseBody.getId());
		assertEquals(mockAccount.getName(), responseBody.getName());
		assertEquals(mockAccount.getBalance(), responseBody.getBalance());
	}

	@Test
	void testGetAccount_NotFound() {

		// Arrange
		long accountId = 2L;
		when(accountService.getAccount(accountId)).thenReturn(Optional.empty());

		// Act
		ResponseEntity<AccountResponse> response = accountController.getAccount(accountId);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

}


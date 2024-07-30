package com.bankjava.proje.controller;

import java.util.List; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankjava.proje.dto.AccountDto;
import com.bankjava.proje.dto.CreateAccountDto;
import com.bankjava.proje.dto.TransferFundDto;
import com.bankjava.proje.service.AccountService;

@RestController
@RequestMapping("/account/api")
public class AccountController {
	
	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}  
	
	@PostMapping("/addaccount")
	public ResponseEntity<AccountDto> addAccount(@RequestBody CreateAccountDto createAccountDto){
		
		return new ResponseEntity<>(accountService.addAccount(createAccountDto), HttpStatus.CREATED );
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		
		return ResponseEntity.ok(accountService.getAccountById(id));
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody double amount){
		 
		return ResponseEntity.ok(accountService.deposit(id, amount));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok().build();	
		
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withDraw(@PathVariable Long id, @RequestBody double amount){	
		return ResponseEntity.ok(accountService.withdraw(id, amount));
		
	}
	
	
	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<AccountDto>>  getAllAccount(){
		
		return ResponseEntity.ok(accountService.getAllAccount());
	}
	  
	@PostMapping("/transfer")
	public ResponseEntity<String> transferFound ( TransferFundDto transferFundDto ){
		accountService.transferFund(transferFundDto);
		return ResponseEntity.ok("Transfer Successfull");
	}

	

}

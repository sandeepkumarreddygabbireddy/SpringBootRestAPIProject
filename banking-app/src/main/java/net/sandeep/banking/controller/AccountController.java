package net.sandeep.banking.controller;

import java.util.List;
import java.util.Map;

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

import net.sandeep.banking.dto.AccountDto;
import net.sandeep.banking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	//Add Account Rest API
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	//Get Account Rest API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	//Deposit Rest API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//Withdraw Rest API
	@PutMapping("/{id}/withdraw")

	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	// Get All Accounts Rest API
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts = accountService.getAllAccounts(); 
		return ResponseEntity.ok(accounts);
	}
	
	//Delete Account Rest API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) { 
		
		accountService.deleteAccount(id);
		
		return ResponseEntity.ok("Account is deleted successfully!");
	
	}
}

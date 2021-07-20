package com.sample.rest.webservices.resfulwebservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.rest.webservices.resfulwebservice.entity.Account;
import com.sample.rest.webservices.resfulwebservice.entity.Transaction;
import com.sample.rest.webservices.resfulwebservice.entity.User;
import com.sample.rest.webservices.resfulwebservice.exception.NotFoundException;
import com.sample.rest.webservices.resfulwebservice.service.AccountRepository;
import com.sample.rest.webservices.resfulwebservice.service.UserRepository;


@RestController
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/users")
	public List<EntityModel<User>> retrieveAllUsers() {
		List<EntityModel<User>> users = userRepository.findAll().stream()
				.map(user -> EntityModel.of(user, 
						linkTo(methodOn(this.getClass()).retrieveUser(user.getId())).withSelfRel())) //
				.collect(Collectors.toList());
		return users;
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new NotFoundException("id-" + id);
		
		EntityModel<User> User = EntityModel.of(user.get(), 
				linkTo(methodOn(this.getClass()).retrieveAllUserAccounts(id)).withRel("all-accounts"));

		return User;
	}
	
	@GetMapping("/users/{id}/accounts")
	public List<EntityModel<Account>> retrieveAllUserAccounts(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new NotFoundException("id-" + id);
		
		
		List<EntityModel<Account>> accounts = user.get().getAccounts().stream()
				.map(account -> EntityModel.of(account, 
						linkTo(methodOn(this.getClass()).retrieveUserAccount(id, account.getAccountNumber())).withSelfRel())) //
				.collect(Collectors.toList());
	
		
		return accounts;
	}
	
	@GetMapping("/users/{id}/accounts/{accountNumber}")
	public EntityModel<Account> retrieveUserAccount(@PathVariable Long id, @PathVariable Long accountNumber) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new NotFoundException("User with id-" + id+ " not found");
		
		Optional<Account> account = accountRepository.findById(accountNumber);
		if (!account.isPresent())
			throw new NotFoundException("Account with number-" + accountNumber+ " not found");
		
		EntityModel<Account> resource = EntityModel.of(account.get(), 
				linkTo(methodOn(this.getClass()).retrieveAllUserAccounts(id)).withRel("all-accounts"),
				linkTo(methodOn(this.getClass()).retrieveAllAccountTransactions(id, accountNumber)).withRel("all-transactions"));

		
		return resource;
	}
	
	@GetMapping("/users/{id}/accounts/{accountNumber}/transactions")
	public List<EntityModel<Transaction>> retrieveAllAccountTransactions(@PathVariable Long id, @PathVariable Long accountNumber) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new NotFoundException("User with id-" + id+ " not found");
		
		Optional<Account> account = accountRepository.findById(accountNumber);
		if (!account.isPresent())
			throw new NotFoundException("Account with number-" + accountNumber+ " not found");
		
		List<EntityModel<Transaction>> transactions = account.get().getTransactions().stream()
				.map(transaction -> EntityModel.of(transaction, 
						linkTo(methodOn(this.getClass()).retrieveUserAccount(id, accountNumber)).withSelfRel())) //
				.collect(Collectors.toList());
	
		
		return transactions;
	}
}

package com.sample.rest.webservices.resfulwebservice.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Size(min=2, message="FirstName should have atleast 2 characters")
	private String firstName;
	
	@Size(min=2, message="LastName should have atleast 2 characters")
	private String lastName;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Account> accounts;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Transaction> transactions;
	
	protected User() {
	}

	public User(Long id, @Size(min = 2, message = "FirstName should have atleast 2 characters") String firstName,
			@Size(min = 2, message = "LastName should have atleast 2 characters") String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}

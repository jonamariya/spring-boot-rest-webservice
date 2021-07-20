package com.sample.rest.webservices.resfulwebservice.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sample.rest.webservices.resfulwebservice.model.AccountType;

@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long accountNumber;

	@Size(min=2, message="AcccountName should have atleast 2 characters")
	private String accountName;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	private String currency;
	
	private BigDecimal amount;
	
	@OneToMany(mappedBy="account", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Transaction> transactions;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	protected Account() {
	}

	/*public Account(Long accountNumber, @Size(min = 2, message = "AcccountName should have atleast 2 characters") String accountName,
			AccountType accountType,String currency, BigDecimal amount) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountType = accountType;
		this.currency = currency;
		this.amount = amount;
	}*/

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Currency getCurrency() {
		return Currency.getInstance(currency);
	}

	public void setCurrency(Currency currency) {
		this.currency = currency.getCurrencyCode();
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountName=" + accountName + ", accountType="
				+ accountType + ", currency=" + currency + ", amount=" + amount + "]";
	}
	
}

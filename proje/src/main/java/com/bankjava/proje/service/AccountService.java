package com.bankjava.proje.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import com.bankjava.proje.dto.AccountDto;
import com.bankjava.proje.dto.AccountDtoConverter;
import com.bankjava.proje.dto.CreateAccountDto;
import com.bankjava.proje.dto.TransactionDto;
import com.bankjava.proje.dto.TransactionDtoConverter;
import com.bankjava.proje.dto.TransferFundDto;
import com.bankjava.proje.exception.AccountException;
import com.bankjava.proje.model.Account;
import com.bankjava.proje.model.Transaction;
import com.bankjava.proje.repository.AccountRepository;
import com.bankjava.proje.repository.TransactionRepository;

@Service
public class AccountService {
	
	
	private final AccountRepository accountRepository;
	private final AccountDtoConverter accountDtoConverter;
	private final TransactionRepository transactionRepository;
	private final TransactionDtoConverter transactionDtoConverter;
	
	public AccountService(AccountRepository accountRepository, AccountDtoConverter accountDtoConverter,
			TransactionRepository transactionRepository, TransactionDtoConverter transactionDtoConverter) {
		super();
		this.accountRepository = accountRepository;
		this.accountDtoConverter = accountDtoConverter;
		this.transactionRepository = transactionRepository;
		this.transactionDtoConverter = transactionDtoConverter;
	}

	private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
	private static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
	private static final String TRANSACTION_TYPE_TRANSFER = "TRANSFER";
	


	public AccountDto addAccount(CreateAccountDto createAccountDto) {
		Account account = Account.builder()
				.id(createAccountDto.getId())
				.accountHolderName(createAccountDto.getAccountHolderName())
				.balance(createAccountDto.getBalance())
				.build();
		return accountDtoConverter.convert(accountRepository.save(account));
	}


	public AccountDto getAccountById(Long id) {
		Optional<Account> accountOptional = accountRepository.findById(id);
		return accountOptional.map(account -> accountDtoConverter.convert(account))
				.orElse(new AccountDto());
	}


	public AccountDto deposit(Long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(()-> new AccountException("Account does not exists"));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		
		Transaction transaction = new Transaction();
		transaction.setAccountId(id);
		transaction.setAmount(amount);
		transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
		transaction.setTimestamp(LocalDateTime.now());
		transactionRepository.save(transaction);
		
		return accountDtoConverter.convert(savedAccount);
	}


	public void deleteAccount(Long id) {
		Account deleteAccount= accountRepository.findById(id)
				.orElseThrow(() -> new AccountException("Account does not exists"));
			accountRepository.delete(deleteAccount);
	}


	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(()-> new AccountException("Account does not exists"));
		
		if (account.getBalance()< amount) {
			throw new RuntimeException("Insufficient Amount ");			
		}
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		
		Transaction transaction = new Transaction();
		transaction.setAccountId(id);
		transaction.setAmount(amount);
		transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
		transaction.setTimestamp(LocalDateTime.now());
		
		transactionRepository.save(transaction);
		return accountDtoConverter.convert(savedAccount);
	}


	public List<AccountDto> getAllAccount() {
		List<Account> accountList = accountRepository.findAll();
		List<AccountDto> accountDtoList= new ArrayList<>();
		
		for (Account account:  accountList) {
	
			accountDtoList.add(accountDtoConverter.convert(account));
		}
		return accountDtoList;
	}
	
	
	public void transferFund (TransferFundDto transferFundDto) {
		
		Account fromAccount = accountRepository.findById(transferFundDto.fromAccountId())
		.orElseThrow(()-> new AccountException("Account does not exists"));
		
		Account toAccount = accountRepository.findById(transferFundDto.toAccountId())
				.orElseThrow(()-> new AccountException("Account does not exists"));
	
		if(fromAccount.getBalance()<transferFundDto.amount()) {
			throw new RuntimeException("Insufficient Amount");
		}
		fromAccount.setBalance(fromAccount.getBalance()-transferFundDto.amount());
		toAccount.setBalance(toAccount.getBalance() + transferFundDto.amount());
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		Transaction transaction = new Transaction();
		transaction.setAccountId(transferFundDto.fromAccountId());
		transaction.setAmount(transferFundDto.amount());
		transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
		transaction.setTimestamp(LocalDateTime.now());
		transactionRepository.save(transaction);
		
	}
	
	public List<TransactionDto> getAccountTransactions(){
		
		List<Transaction> transactionList = transactionRepository.findAll();
		List<TransactionDto> transactionDtoList = new ArrayList<>();
		
		for(Transaction transaction: transactionList) {
			
		transactionDtoList.add(transactionDtoConverter.convert(transaction));
		}
		return transactionDtoList;
		}
}








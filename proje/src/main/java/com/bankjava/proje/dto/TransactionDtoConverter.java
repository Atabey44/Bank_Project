package com.bankjava.proje.dto;

import com.bankjava.proje.model.Transaction;

public class TransactionDtoConverter {
	
	
	public TransactionDto convert (Transaction transaction) {
	
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setAccountId(transaction.getAccountId());
		transactionDto.setAmount(transaction.getAmount());
		transactionDto.setTransactionType(transaction.getTransactionType());
		transactionDto.setTimestamp(transaction.getTimestamp());
		return transactionDto;
	}
	
}

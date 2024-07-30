package com.bankjava.proje.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto{
	
	
	
		private Long id;
		private Long accountId;
		private double amount;
		private String transactionType;
		private LocalDateTime timestamp;
}

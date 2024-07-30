package com.bankjava.proje.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountDto {

	private Long id;
	private String accountHolderName;
	private double balance;
	private double amount;

}

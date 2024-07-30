package com.bankjava.proje.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAccountDto {
	
	private String accountHolderName;
	private double balance;
	private double amount;


}

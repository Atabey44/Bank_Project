package com.bankjava.proje.dto;

public record TransferFundDto(Long fromAccountId,
							Long toAccountId,
							double amount
							) {

}

package com.bankjava.proje.dto;

import org.springframework.stereotype.Component;

import com.bankjava.proje.model.Account;


@Component
public class AccountDtoConverter {
	
	
	
	public AccountDto convert(Account account) {
		
		AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setAccountHolderName(account.getAccountHolderName());
        return accountDto;
    }


}

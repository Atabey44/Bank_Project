package com.bankjava.proje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankjava.proje.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,  Long>{
	
	

}

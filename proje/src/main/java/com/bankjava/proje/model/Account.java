package com.bankjava.proje.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

  
@Entity
@Data
@Table(name= "accounts")
@Builder
public class Account {
 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="account_holder_name")
	private String accountHolderName;
	
	@Column(name="balance")
	private double balance;
	
	@Column(name="amount")
	private double amount;
}

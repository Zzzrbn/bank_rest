package com.example.bankcards.entity;

import java.util.Date;

import com.example.bankcards.util.CardStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "bankcards")
public class Bankcard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "number", nullable = false, unique = true)
	@Size(min = 16, max = 16, message = "The card number must consist of sixteen charasters!")
	private String number;
	
	@Column(name = "holder")
	private String holder;
	
	@Column(name = "expdate")
	private Date expdate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private CardStatus status;
	
	@Column(name = "balance")
	private double balance;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		
		this.number = number;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public Date getExpdate() {
		return expdate;
	}

	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}

	public CardStatus getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "Card [id=" + id + ", number=" + number + ", holder=" + holder + ", expdate=" + expdate + ", status="
			+ status + ", balance=" + balance + "]";
	}
	
	public Bankcard() {
		super();
	}

	public Bankcard(int id, String number, String holder, Date expdate, CardStatus status, double balance) {
		super();
		
		this.id = id;
		this.number = number;
		this.holder = holder;
		this.expdate = expdate;
		this.status = status;
		this.balance = balance;
	}
	
	public Bankcard(String number, String holder, Date expdate, CardStatus status, double balance) {
		super();
		
		this.number = number;
		this.holder = holder;
		this.expdate = expdate;
		this.status = status;
		this.balance = balance;
	}



	
	
	
	
	
}

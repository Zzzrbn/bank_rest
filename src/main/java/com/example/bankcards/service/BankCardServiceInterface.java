package com.example.bankcards.service;

import com.example.bankcards.entity.Bankcard;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BankCardServiceInterface {
	
	public List<Bankcard> getBankCards();
	
	public List<Bankcard> getMyCards(String name);
	
	public void createCard(Bankcard bankCard);
	
	public Bankcard getBankCard (int id);
	
	public void deleteBankCard(int id);
	
	public void blockBankCard(int id);
	
	public void activateBankCard(int id);
	
	public String sendMoney(int id1, int id2, double amount);

}

package com.example.bankcards.dto;

import java.util.List;
import com.example.bankcards.entity.Bankcard;

public interface CardDTOInterface {
	
	public List<Bankcard> getAllCards();
	
	public List<Bankcard> getMyCards(String name);
	
	public void createCard(Bankcard bankCard);
	
	public Bankcard getBankCard (int id);
	
	public void deleteBankCard(int id);
	
	public void blockBankCard(int id);
	
	public void activateBankCard(int id);
	
	public String sendMoney(int id1, int id2, double amount);
	
	
	

}

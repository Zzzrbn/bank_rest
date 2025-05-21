package com.example.bankcards.service;

import com.example.bankcards.dto.CardDTOInterface;
import com.example.bankcards.entity.Bankcard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BankCardService implements BankCardServiceInterface
{
	@Autowired
	private CardDTOInterface cardDTOInterface;

	@Override
	@Transactional
	public List<Bankcard> getBankCards() {
		return cardDTOInterface.getAllCards();
	}
	
	@Override
	@Transactional
	public List<Bankcard> getMyCards(String name) {
		return cardDTOInterface.getMyCards(name);

	}

	@Override
	@Transactional
	public void createCard(Bankcard bankCard) {
		cardDTOInterface.createCard(bankCard);
	}

	@Override
	@Transactional
	public Bankcard getBankCard(int id) {
		return cardDTOInterface.getBankCard(id);
	}

	@Override
	@Transactional
	public void deleteBankCard(int id) {
		cardDTOInterface.deleteBankCard(id);		
	}

	@Override
	@Transactional
	public void blockBankCard(int id) {
		cardDTOInterface.blockBankCard(id);		
	}

	@Override
	@Transactional
	public void activateBankCard(int id) {
		cardDTOInterface.activateBankCard(id);		
	}

	@Override
	@Transactional
	public String sendMoney(int id1, int id2, double amount) {
		return cardDTOInterface.sendMoney(id1, id2, amount);		
	}
	
	
}

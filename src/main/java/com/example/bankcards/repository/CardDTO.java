package com.example.bankcards.repository;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.example.bankcards.dto.CardDTOInterface;
import com.example.bankcards.entity.Bankcard;
import com.example.bankcards.exception.NoSuchBankCardException;
import com.example.bankcards.util.CardStatus;

import jakarta.persistence.EntityManager;


@Repository
public class CardDTO  implements CardDTOInterface{

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Bankcard> getAllCards() {
		Session session = entityManager.unwrap(Session.class);
		Query<Bankcard> query = session.createQuery("from Bankcard", Bankcard.class);
		List<Bankcard> allBankCards = query.getResultList();
		for (Bankcard card : allBankCards) {
			session.evict(card);
			String numString = card.getNumber();
			card.setNumber("**** **** **** " + numString.substring(12));
		}
		return allBankCards;
	}
	
	@Override
	public List<Bankcard> getMyCards(String name) {
		Session session = entityManager.unwrap(Session.class);
		Query<Bankcard> query = session.createQuery("from Bankcard where holder =: nameholder", Bankcard.class);
		query.setParameter("nameholder", name);
		List<Bankcard> MyBankCards = query.getResultList();
		for (Bankcard card : MyBankCards) {
			session.evict(card);
			String numString = card.getNumber();
			card.setNumber("**** **** **** " + numString.substring(12));
		}
		return MyBankCards;
	}

	@Override
	public void createCard(Bankcard bankCard) {
		Session session = entityManager.unwrap(Session.class);
		session.persist(bankCard);
	}
	
	@Override
	public Bankcard getBankCard(int id) {
		Session session = entityManager.unwrap(Session.class);
		Bankcard bankcard = session.get(Bankcard.class, id);
		if (bankcard!=null) {
		session.evict(bankcard);
		String numString = bankcard.getNumber();
		bankcard.setNumber("**** **** **** " + numString.substring(12));
		return bankcard;}
		else {
			throw new NoSuchBankCardException("Cannot find Bank card with id=" + id);
		}
		}
	
	@Override
	public void deleteBankCard(int id) {		
		Session session = entityManager.unwrap(Session.class);
		Bankcard bankcard = session.get(Bankcard.class, id);
		if (bankcard == null) {
			throw new NoSuchBankCardException("Cannot find Bank card with id=" + id);
		}
		Query<Bankcard> query = session.createQuery("delete from Bankcard where id =: bankcardId"
				, null);
		query.setParameter("bankcardId", id);
		query.executeUpdate();		
	}

	@Override
	public void blockBankCard(int id) {
		Session session = entityManager.unwrap(Session.class);
		Bankcard bankcard = session.get(Bankcard.class, id);
		if (bankcard == null) {
			throw new NoSuchBankCardException("Cannot find Bank card with id=" + id);
		}
		bankcard.setStatus(CardStatus.BLOCKED);
		session.merge(bankcard);
	}

	@Override
	public void activateBankCard(int id) {
		Session session = entityManager.unwrap(Session.class);
		Bankcard bankcard = session.get(Bankcard.class, id);
		if (bankcard == null) {
			throw new NoSuchBankCardException("Cannot find Bank card with id=" + id);
		}
		bankcard.setStatus(CardStatus.ACTIVE);
		session.merge(bankcard);		
	}
	
	@Override
	public String sendMoney(int id1, int id2, double amount) {		
		Session session = entityManager.unwrap(Session.class);
		Bankcard bankcard1 = session.get(Bankcard.class, id1);
		Bankcard bankcard2 = session.get(Bankcard.class, id2);
		if (bankcard1.getBalance() > amount && bankcard2 != null 
				&& bankcard1.getHolder().equals(bankcard2.getHolder())
				&& bankcard1.getHolder().equals(SecurityContextHolder.getContext().getAuthentication().getName())
				&& (bankcard1.getStatus() == CardStatus.ACTIVE) && (bankcard2.getStatus() == CardStatus.ACTIVE)) 
		{
			bankcard1.setBalance(bankcard1.getBalance() - amount);
			bankcard2.setBalance(bankcard2.getBalance() + amount);
			session.evict(bankcard2);
			session.evict(bankcard1);
			session.merge(bankcard2);
			session.merge(bankcard1);
		} else {
			return "Insufficient funds in the account or incorrect data";
		}
		return "Amount is sent";
	}
	
	
	
	

}

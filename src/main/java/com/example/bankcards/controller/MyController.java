package com.example.bankcards.controller;

import com.example.bankcards.entity.Bankcard;
import com.example.bankcards.exception.BankCardIncorrectData;
import com.example.bankcards.exception.NoSuchBankCardException;
import com.example.bankcards.service.BankCardServiceInterface;
import com.example.bankcards.util.CardStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {

	@Autowired
	private BankCardServiceInterface bankCardServiceInterface;

	@GetMapping("/admin/cards")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Bankcard> showAllCards() {
		List<Bankcard> allCards = bankCardServiceInterface.getBankCards();
		return allCards;
	}
	
	@GetMapping("/user/cards")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public List<Bankcard> showMyCards() {
		List<Bankcard> MyCards = bankCardServiceInterface
				.getMyCards(SecurityContextHolder.getContext().getAuthentication().getName());
		return MyCards;
	}

	@GetMapping("/admin/cards/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Bankcard getBankcard(@PathVariable int id) {
		Bankcard bankcard = bankCardServiceInterface.getBankCard(id);
		return bankcard;
	}

	@GetMapping("/admin/newcard/{number}/{holder}/{expdate}/{status}/{balance}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Bankcard addNewBankcard(@PathVariable String number, 
			@PathVariable String holder,
			@PathVariable String expdate, 
			@PathVariable CardStatus status, 
			@PathVariable double balance)
			throws ParseException {
		if (!number.matches("\\d+"))
		{
		throw new NoSuchBankCardException("Wrong card number");
		}
		DateFormat dFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date dateCard = dFormat.parse(expdate);
		Bankcard newBankcard = new Bankcard(number, holder, dateCard, status, balance);
		bankCardServiceInterface.createCard(newBankcard);
		return newBankcard;
	}

	@GetMapping("/admin/delete/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteBankcard(@PathVariable int id) {
		bankCardServiceInterface.deleteBankCard(id);
		return "Bank Card with id = " + id + " was deleted";
	}

	@GetMapping("/admin/activate/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void activateBankcard(@PathVariable int id) {
		bankCardServiceInterface.activateBankCard(id);		
	}

	@GetMapping("/admin/block/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void blockBankcard(@PathVariable int id) {
		bankCardServiceInterface.blockBankCard(id);		
	}

	@GetMapping("/user/cards/{id1}/{id2}/{amount}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public String sendMoney(@PathVariable int id1, @PathVariable int id2, @PathVariable double amount) {
		return bankCardServiceInterface.sendMoney(id1, id2, amount);
	}

	@ExceptionHandler
	public ResponseEntity<BankCardIncorrectData> handleException(NoSuchBankCardException exception) {
		BankCardIncorrectData data = new BankCardIncorrectData();
		data.setInfo(exception.getMessage());
		return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<BankCardIncorrectData> handleException(Exception exception) {
		BankCardIncorrectData data = new BankCardIncorrectData();
		data.setInfo(exception.getMessage());
		return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
	}
	
}

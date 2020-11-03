package com.gomeschristopher.store.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gomeschristopher.store.domain.Client;
import com.gomeschristopher.store.repositories.ClientRepository;
import com.gomeschristopher.store.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("EMAIL_NOT_FOUND");
		}
		
		String newPass = newPassword();
		client.setPassword(pe.encode(newPass));
		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
	}
	
	private String newPassword() {
		char[]vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt();
		if (opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) {
		    return (char) (rand.nextInt(26) + 65);
		} 
		else {
			return (char) (rand.nextInt(26) + 97);
		}
	}
}

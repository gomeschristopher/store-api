package com.gomeschristopher.store.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.gomeschristopher.store.domain.Client;
import com.gomeschristopher.store.domain.Purchase;

public interface EmailService {

	void sendPurchaseConfirmationEmail(Purchase obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Purchase obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Client client, String newPass);
}

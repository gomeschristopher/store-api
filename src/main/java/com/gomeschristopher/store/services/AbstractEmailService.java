package com.gomeschristopher.store.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.gomeschristopher.store.domain.Purchase;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendPurchaseConfirmationEmail(Purchase obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Purchase obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Peido confirmado. Codigo: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Purchase obj) {
		Context context = new Context();
		context.setVariable("purchase", obj);
		return templateEngine.process("email/purchase_confirmation", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Purchase obj) {
		try {
			MimeMessage mm = prepareMimeMessageFormPedido(obj);
			sendHtmlEmail(mm);
		}
		catch(MessagingException e) {
			sendPurchaseConfirmationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMimeMessageFormPedido(Purchase obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado, cod.: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}
}

package com.gomeschristopher.store.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gomeschristopher.store.domain.PaymentBill;
import com.gomeschristopher.store.domain.Purchase;
import com.gomeschristopher.store.domain.PurchaseItem;
import com.gomeschristopher.store.domain.enums.PaymentStatus;
import com.gomeschristopher.store.repositories.PaymentRepository;
import com.gomeschristopher.store.repositories.PurchaseItemRepository;
import com.gomeschristopher.store.repositories.PurchaseRepository;
import com.gomeschristopher.store.services.exceptions.ObjectNotFoundException;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository repo;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;

	public Purchase find(Integer id) {
		Optional<Purchase> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Purchase.class.getName()));
	}
	
	@Transactional
	public Purchase insert(Purchase obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setStatus(PaymentStatus.PENDING);
		obj.getPayment().setPurchase(obj);
		if(obj.getPayment() instanceof PaymentBill) {
			PaymentBill payment = (PaymentBill) obj.getPayment();
			billService.fillBillPayment(payment, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (PurchaseItem ip : obj.getItems()) {
			ip.setDescount(0.0);
			ip.setProduct(productService.find(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setPurchase(obj);
		}
		purchaseItemRepository.saveAll(obj.getItems());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
		
	}
	
}

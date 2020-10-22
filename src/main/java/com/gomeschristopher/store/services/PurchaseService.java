package com.gomeschristopher.store.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomeschristopher.store.domain.Purchase;
import com.gomeschristopher.store.repositories.PurchaseRepository;
import com.gomeschristopher.store.services.exceptions.ObjectNotFoundException;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository repo;

	public Purchase find(Integer id) {
		Optional<Purchase> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Purchase.class.getName()));
	}
	
}

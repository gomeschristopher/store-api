package com.gomeschristopher.store.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gomeschristopher.store.domain.Purchase;
import com.gomeschristopher.store.services.PurchaseService;

@RestController
@RequestMapping(value="/purchases")
public class PurchaseResource {
	
	@Autowired
	private PurchaseService service;

	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Purchase obj = service.find(id);
		return ResponseEntity.ok().body(obj); 
	}
	
}

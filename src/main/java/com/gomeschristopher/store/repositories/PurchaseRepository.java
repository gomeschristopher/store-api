package com.gomeschristopher.store.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gomeschristopher.store.domain.Client;
import com.gomeschristopher.store.domain.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	
	@Transactional(readOnly=true)
	Page<Purchase> findByClient(Client client, Pageable pageRequest);

}

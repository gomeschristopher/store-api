package com.gomeschristopher.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomeschristopher.store.domain.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

}

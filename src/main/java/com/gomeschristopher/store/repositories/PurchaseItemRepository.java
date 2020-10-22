package com.gomeschristopher.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomeschristopher.store.domain.PurchaseItem;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Integer> {

}

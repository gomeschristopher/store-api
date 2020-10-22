package com.gomeschristopher.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomeschristopher.store.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}

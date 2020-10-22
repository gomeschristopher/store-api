package com.gomeschristopher.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomeschristopher.store.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

package com.gomeschristopher.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomeschristopher.store.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}

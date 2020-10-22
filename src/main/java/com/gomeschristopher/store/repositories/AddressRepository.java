package com.gomeschristopher.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomeschristopher.store.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}

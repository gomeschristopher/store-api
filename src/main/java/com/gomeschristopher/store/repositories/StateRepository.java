package com.gomeschristopher.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomeschristopher.store.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}

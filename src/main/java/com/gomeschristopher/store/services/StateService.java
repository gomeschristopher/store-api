package com.gomeschristopher.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomeschristopher.store.domain.State;
import com.gomeschristopher.store.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;
	
	public List<State> findAll() {
		return repo.findAllByOrderByName();
	}
}

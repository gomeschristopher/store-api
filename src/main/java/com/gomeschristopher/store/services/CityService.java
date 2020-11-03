package com.gomeschristopher.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomeschristopher.store.domain.City;
import com.gomeschristopher.store.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;

	public List<City> findByState(Integer stateId) {
		return repo.findCities(stateId);
	}
}

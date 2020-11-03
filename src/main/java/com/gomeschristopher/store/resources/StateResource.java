package com.gomeschristopher.store.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gomeschristopher.store.domain.City;
import com.gomeschristopher.store.domain.State;
import com.gomeschristopher.store.dto.CityDTO;
import com.gomeschristopher.store.dto.StateDTO;
import com.gomeschristopher.store.services.CityService;
import com.gomeschristopher.store.services.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

	@Autowired
	private StateService service;

	@Autowired
	private CityService CityService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll() {
		List<State> list = service.findAll();
		List<StateDTO> listDto = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{stateId}/cities", method = RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
		List<City> list = CityService.findByState(stateId);
		List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}

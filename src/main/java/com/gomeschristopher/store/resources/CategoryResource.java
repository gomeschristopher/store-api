package com.gomeschristopher.store.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gomeschristopher.store.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Category> listar() {
		
		Category car1 = new Category(1, "Informatica");
		Category cat2 = new Category(2, "Escritorio");
		
		List<Category> list = new ArrayList<>();
		list.add(car1);
		list.add(cat2);
		
		return list; 
	}
	
}

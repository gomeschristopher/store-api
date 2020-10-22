package com.gomeschristopher.store;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gomeschristopher.store.domain.Address;
import com.gomeschristopher.store.domain.Category;
import com.gomeschristopher.store.domain.City;
import com.gomeschristopher.store.domain.Client;
import com.gomeschristopher.store.domain.Product;
import com.gomeschristopher.store.domain.State;
import com.gomeschristopher.store.domain.enums.ClientType;
import com.gomeschristopher.store.repositories.AddressRepository;
import com.gomeschristopher.store.repositories.CategoryRepository;
import com.gomeschristopher.store.repositories.CityRepository;
import com.gomeschristopher.store.repositories.ClientRepository;
import com.gomeschristopher.store.repositories.ProductRepository;
import com.gomeschristopher.store.repositories.StateRepository;

@SpringBootApplication
public class StoreApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Empressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2)); 
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "Sao Paulo");
		
		City city1 = new City(null, "Uberlandia", state1);
		City city2 = new City(null, "Sao Paulo", state2);
		City city3 = new City(null, "Campinas", state2);
		
		state1.getCity().addAll(Arrays.asList(city1));
		state2.getCity().addAll(Arrays.asList(city2, city3));
		
		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "28192010163", ClientType.NATURAL_PERSON); 
		cli1.getPhones().addAll(Arrays.asList("983077539", "39991599"));
		
		Address address1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, city1);
		Address address2 = new Address(null, "Av Matos", "300", "Apto 303", "Jardim", "38220834", cli1, city2);
		
		cli1.getAddress().addAll(Arrays.asList(address1, address2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(address1, address2));
		
	}
	
	

}

package com.gomeschristopher.store;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gomeschristopher.store.domain.Address;
import com.gomeschristopher.store.domain.Category;
import com.gomeschristopher.store.domain.City;
import com.gomeschristopher.store.domain.Client;
import com.gomeschristopher.store.domain.Payment;
import com.gomeschristopher.store.domain.PaymentBill;
import com.gomeschristopher.store.domain.PaymentCard;
import com.gomeschristopher.store.domain.Product;
import com.gomeschristopher.store.domain.Purchase;
import com.gomeschristopher.store.domain.PurchaseItem;
import com.gomeschristopher.store.domain.State;
import com.gomeschristopher.store.domain.enums.ClientType;
import com.gomeschristopher.store.domain.enums.PaymentStatus;
import com.gomeschristopher.store.repositories.AddressRepository;
import com.gomeschristopher.store.repositories.CategoryRepository;
import com.gomeschristopher.store.repositories.CityRepository;
import com.gomeschristopher.store.repositories.ClientRepository;
import com.gomeschristopher.store.repositories.PaymentRepository;
import com.gomeschristopher.store.repositories.ProductRepository;
import com.gomeschristopher.store.repositories.PurchaseItemRepository;
import com.gomeschristopher.store.repositories.PurchaseRepository;
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
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;

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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Purchase purchase1 = new Purchase(null, sdf.parse("22/10/2020 10:23"), cli1, address1);
		Purchase purchase2 = new Purchase(null, sdf.parse("21/09/2020 10:23"), cli1, address2);
		
		Payment payment1 = new PaymentCard(null, PaymentStatus.SETTLED, purchase1, 6);
		purchase1.setPayment(payment1);
		
		Payment payment2 = new PaymentBill(null, PaymentStatus.PENDING, purchase2, sdf.parse("22/10/2020 10:23"), sdf.parse("22/10/2020 10:23"));
		purchase2.setPayment(payment2);
		
		cli1.getPurchases().addAll(Arrays.asList(purchase1, purchase2));
		
		purchaseRepository.saveAll(Arrays.asList(purchase1, purchase2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));
		
		PurchaseItem pi1 = new PurchaseItem(purchase1, p1, 0.00, 1, 2000.00);
		PurchaseItem pi2 = new PurchaseItem(purchase1, p3, 0.00, 2, 80.00);
		PurchaseItem pi3 = new PurchaseItem(purchase2, p2, 100.00, 1, 800.00);
		
		purchase1.getItems().addAll(Arrays.asList(pi1, pi2));
		purchase1.getItems().addAll(Arrays.asList(pi1, pi2));
		
		p1.getItems().addAll(Arrays.asList(pi1));
		p2.getItems().addAll(Arrays.asList(pi3));
		p3.getItems().addAll(Arrays.asList(pi2));
		
		purchaseItemRepository.saveAll(Arrays.asList(pi1, pi2, pi3));
	}
	
	

}

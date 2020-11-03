package com.gomeschristopher.store.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gomeschristopher.store.domain.Address;
import com.gomeschristopher.store.domain.City;
import com.gomeschristopher.store.domain.Client;
import com.gomeschristopher.store.domain.enums.ClientType;
import com.gomeschristopher.store.domain.enums.Profile;
import com.gomeschristopher.store.dto.ClientDTO;
import com.gomeschristopher.store.dto.ClientNewDTO;
import com.gomeschristopher.store.repositories.AddressRepository;
import com.gomeschristopher.store.repositories.ClientRepository;
import com.gomeschristopher.store.security.UserSS;
import com.gomeschristopher.store.services.exceptions.AuthorizationException;
import com.gomeschristopher.store.services.exceptions.DataIntegrityException;
import com.gomeschristopher.store.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Client find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("ACCESS_DENIED");
		}
		
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddress());
		return obj;
	}
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("CLIENT_HAS_PURCHASE");
		}
		
	}
	
	public List<Client> findAll() {
		return repo.findAll();
	}
	
	public Client findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
	
		Client obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Client.class.getName());
		}
		return obj;
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getDocument(), ClientType.toEnum(objDto.getType()), pe.encode(objDto.getPassword()));
		City cid = new City(objDto.getCityId(), null, null);
		Address end = new Address(null, objDto.getStreet(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getZipcode(), cli, cid);
		cli.getAddress().add(end);
		cli.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2()!=null) {
			cli.getPhones().add(objDto.getPhone2());
		}
		if (objDto.getPhone3()!=null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String filename = prefix  + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), filename, "image");

	}
	
}

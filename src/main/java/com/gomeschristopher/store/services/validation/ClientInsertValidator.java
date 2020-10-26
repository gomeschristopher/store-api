package com.gomeschristopher.store.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gomeschristopher.store.domain.Client;
import com.gomeschristopher.store.domain.enums.ClientType;
import com.gomeschristopher.store.dto.ClientNewDTO;
import com.gomeschristopher.store.repositories.ClientRepository;
import com.gomeschristopher.store.resources.exeption.FieldMessage;
import com.gomeschristopher.store.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		if(objDto.getType().equals(ClientType.NATURAL_PERSON.getCod()) && !BR.isValidCPF(objDto.getDocument())) {
			list.add(new FieldMessage("document", "INVALID_FIELD"));
		}
		
		if(objDto.getType().equals(ClientType.JURIDICAL_PERSON.getCod()) && !BR.isValidCNPJ(objDto.getDocument())) {
			list.add(new FieldMessage("document", "INVALID_FIELD"));
		}
		
		Client aux = clientRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "EMAIL_ALREADY_EXIST"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

package com.gomeschristopher.store.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gomeschristopher.store.domain.Client;
import com.gomeschristopher.store.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	@Length(min=5, max=120, message="LENGHT_INVALID")
	private String name;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	@Email(message="INVALID_EMAIL")
	private String email;
	
	public ClientDTO() {
		
	}
	
	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

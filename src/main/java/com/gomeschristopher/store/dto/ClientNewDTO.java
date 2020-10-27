package com.gomeschristopher.store.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gomeschristopher.store.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	@Length(min=5, max=120, message="LENGHT_INVALID")
	private String name;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	@Email(message="INVALID_EMAIL")
	private String email;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	private String document;
	
	private Integer type;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	private String password;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	private String street;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	private String number;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	private String complement;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	private String district;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	private String zipcode;
	
	@NotEmpty(message="OBRIGATORY_FIELD")
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Integer cityId;
	
	public ClientNewDTO() {
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

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	

}

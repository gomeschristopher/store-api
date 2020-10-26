package com.gomeschristopher.store.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gomeschristopher.store.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("cardPayment")
public class PaymentCard extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer installmentsNumber;
	
	public PaymentCard() {
	}

	public PaymentCard(Integer id, PaymentStatus status, Purchase order, Integer installmentsNumber) {
		super(id, status, order);
		this.installmentsNumber = installmentsNumber;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}
	
	
	
	
	
}

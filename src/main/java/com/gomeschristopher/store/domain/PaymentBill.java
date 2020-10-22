package com.gomeschristopher.store.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gomeschristopher.store.domain.enums.PaymentStatus;

@Entity
public class PaymentBill extends Payment {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date paymentDate;
	
	public PaymentBill() {	
	}

	public PaymentBill(Integer id, PaymentStatus status, Purchase order, Date dueDate, Date paymentDate) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
	
}

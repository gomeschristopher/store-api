package com.gomeschristopher.store.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PurchaseItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private PurchaseItemPK id = new PurchaseItemPK();

	private Double descount;
	private Integer quantity;
	private Double price;
	
	public PurchaseItem() {
	}

	public PurchaseItem(Purchase purchase, Product product, Double descount, Integer quantity, Double price) {
		super();
		id.setPurchase(purchase);
		id.setProduct(product);
		this.descount = descount;
		this.quantity = quantity;
		this.price = price;
	}
	
	public double getSubtotal() {
		return (price - descount) * quantity;
	}
	
	@JsonIgnore
	public Purchase getPurchase() {
		return id.getPurchase();
	}
	
	public void setPurchase(Purchase purchase) {
		id.setPurchase(purchase);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public PurchaseItemPK getId() {
		return id;
	}

	public void setId(PurchaseItemPK id) {
		this.id = id;
	}

	public Double getDescount() {
		return descount;
	}

	public void setDescount(Double descount) {
		this.descount = descount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseItem other = (PurchaseItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}

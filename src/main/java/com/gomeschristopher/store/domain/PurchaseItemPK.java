package com.gomeschristopher.store.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PurchaseItemPK  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="purchase_id")
	private Purchase purchase;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((purchase == null) ? 0 : purchase.hashCode());
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
		PurchaseItemPK other = (PurchaseItemPK) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (purchase == null) {
			if (other.purchase != null)
				return false;
		} else if (!purchase.equals(other.purchase))
			return false;
		return true;
	}
	
	
	
}

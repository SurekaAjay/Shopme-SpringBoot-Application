package com.shopme.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.shopme.common.entity.product.Product;


@Entity
@Table(name = "cart_items")
public class CartItem extends IdBasedEntity {
	

	
	@ManyToOne  //Many cart items can be there in 1 customer id
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne   //one product can have multiple quantities
	@JoinColumn(name = "product_id")	
	private Product product;
	
	private int quantity;
	
//	Transient annotation is used to indicate that a field is not to be persisted in the database
	@Transient   //
	private float shippingCost;
	
	public CartItem() {
	}

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", customer=" + customer.getFullName() + ", product=" + product.getShortName() + ", quantity=" + quantity
				+ "]";
	}

	@Transient
	public float getSubtotal() {
		return product.getDiscountPrice() * quantity;
	}

	@Transient
	public float getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	
}
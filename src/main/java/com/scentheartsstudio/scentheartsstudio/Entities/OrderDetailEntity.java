package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_order_detail")
public class OrderDetailEntity extends BaseProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private Long order_header_id;

	@Column
	private Long cart_id;


	@Column
	private Double shipping_cost;

	@Column
	private String shipping_address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrder_header_id() {
		return order_header_id;
	}

	public void setOrder_header_id(Long order_header_id) {
		this.order_header_id = order_header_id;
	}

	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}

	public Double getShipping_cost() {
		return shipping_cost;
	}

	public void setShipping_cost(Double shipping_cost) {
		this.shipping_cost = shipping_cost;
	}

	public String getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
}

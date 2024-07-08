package com.scentheartsstudio.scentheartsstudio.DTO;

public class PostCartDTO {
	private Long id;
	private Long product_id;
	private Long user_id;
	private Long product_size_id;
	private Integer quantity;
	private Double total_price;

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getProduct_size_id() {
		return product_size_id;
	}

	public void setProduct_size_id(Long product_size_id) {
		this.product_size_id = product_size_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

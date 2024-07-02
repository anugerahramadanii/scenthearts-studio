package com.scentheartsstudio.scentheartsstudio.DTO;

import java.util.List;

public class PostProductDTO {
	private Long id;
	private Long category_id;
	private String name;
	private String description;
	private List<String> image_path;
	private List<PostProductSizeDTO> sizes;
	private Double real_price;
	private Double discount_rate;
	private Double discount_price;
	private Boolean active;
	private Long user_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImage_path() {
		return image_path;
	}

	public void setImage_path(List<String> image_path) {
		this.image_path = image_path;
	}


	public List<PostProductSizeDTO> getSizes() {
		return sizes;
	}

	public void setSizes(List<PostProductSizeDTO> sizes) {
		this.sizes = sizes;
	}

	public Double getReal_price() {
		return real_price;
	}

	public void setReal_price(Double real_price) {
		this.real_price = real_price;
	}

	public Double getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(Double discount_rate) {
		this.discount_rate = discount_rate;
	}

	public Double getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(Double discount_price) {
		this.discount_price = discount_price;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}

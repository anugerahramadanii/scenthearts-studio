package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_product_size")
public class ProductSizeEntity extends BaseProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false, length = 5)
	private String name;

	@Column
	private Boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}

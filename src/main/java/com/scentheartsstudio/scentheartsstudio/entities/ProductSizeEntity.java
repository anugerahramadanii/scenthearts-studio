package com.scentheartsstudio.scentheartsstudio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_product_size")
@Getter
@Setter
public class ProductSizeEntity extends BaseProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private Long product_id;

	@Column(nullable = false, length = 5)
	private String size_name;

	@Column
	private Integer stock;

	@Column
	private Boolean active;
}

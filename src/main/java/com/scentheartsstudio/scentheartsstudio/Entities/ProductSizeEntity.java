package com.scentheartsstudio.scentheartsstudio.Entities;

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

	@Column(nullable = false, length = 5)
	private String name;

	@Column
	private Boolean active;
}

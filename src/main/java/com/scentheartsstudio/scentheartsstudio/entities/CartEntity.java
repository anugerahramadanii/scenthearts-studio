package com.scentheartsstudio.scentheartsstudio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_cart")
@Getter
@Setter
public class CartEntity extends BaseProperties  {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long product_id;

	@Column(nullable = false)
	private Long user_id;

	@Column
	private Long product_size_id;

	@Column
	private Integer quantity;

	@Column
	private Double product_price;

	@Column
	private Double total_price;
}

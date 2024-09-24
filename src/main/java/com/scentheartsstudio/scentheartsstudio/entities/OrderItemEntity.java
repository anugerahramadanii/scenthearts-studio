package com.scentheartsstudio.scentheartsstudio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "t_order_item")
@Getter
@Setter
public class OrderItemEntity extends BaseProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(nullable = false)
	private Long order_id;

	@Column(nullable = false)
	private Long product_size_id;

	@Column(nullable = false)
	private Long product_id;

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false, length = 18, precision = 4)
	private Double total_amount;

	@Column(nullable = false)
	private Boolean active;
}

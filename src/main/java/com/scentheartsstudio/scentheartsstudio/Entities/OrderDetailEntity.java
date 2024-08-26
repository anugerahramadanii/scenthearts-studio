package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_order_detail")
@Getter
@Setter
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
}

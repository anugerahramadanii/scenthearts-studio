package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "t_order_header")
@Getter
@Setter
public class OrderHeaderEntity extends BaseProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 15)
	private String reference;

	@Column
	private Long user_id;

	@Column
	private Long customer_id;

	@Column
	private Date order_date;

	@Column
	private String order_number;

	@Column(nullable = false, length = 18, precision = 4)
	private Double total_amount;

	@Column
	private String status; // (e.g., pending, completed, cancelled).

	@Column
	private String payment_method;

	@Column
	private String shipping_method;
}

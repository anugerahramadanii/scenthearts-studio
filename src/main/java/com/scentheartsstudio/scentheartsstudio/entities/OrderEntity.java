package com.scentheartsstudio.scentheartsstudio.entities;

import com.scentheartsstudio.scentheartsstudio.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "t_order")
@Getter
@Setter
public class OrderEntity extends BaseProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private Long user_id;

	@Column
	private Date order_date;

	@Column(nullable = false, length = 15)
	private String order_number;

	@Column(nullable = false, length = 18, precision = 4)
	private Double total_amount;

	@Enumerated(EnumType.STRING)
	@Column
	private OrderStatusEnum status;

	@Column(nullable = false)
	private String payment_method;

	@Column(nullable = false)
	private String shipping_address;

	@Column(nullable = false, length = 18, precision = 4)
	private Double shipping_cost;

}

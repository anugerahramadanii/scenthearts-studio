package com.scentheartsstudio.scentheartsstudio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "t_shipping")
@Getter
@Setter
public class ShippingEntity extends BaseProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private Long order_id;

	@Column
	private Date shipping_Date;

	@Column
	private String carrier;

	@Column
	private String tracking_number;

	@Column
	private String status;
}

package com.scentheartsstudio.scentheartsstudio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "t_payment")
@Getter
@Setter
public class PaymentEntity extends BaseProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private Long order_id;

	@Column
	private Date payment_date;

	@Column
	private Double amount;

	@Column
	private String payment_method;

	@Column
	private String payment_status;
}

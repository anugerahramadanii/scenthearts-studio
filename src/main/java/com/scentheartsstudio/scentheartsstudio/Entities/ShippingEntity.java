package com.scentheartsstudio.scentheartsstudio.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_shipping")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Date getShipping_Date() {
		return shipping_Date;
	}

	public void setShipping_Date(Date shipping_Date) {
		this.shipping_Date = shipping_Date;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getTracking_number() {
		return tracking_number;
	}

	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
